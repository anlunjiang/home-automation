package com.aj2814.homeAutomation.services.deviceControllers.tapo;

import com.aj2814.homeAutomation.services.deviceControllers.common.B64Converter;
import com.aj2814.homeAutomation.services.deviceControllers.common.C658a;
import com.aj2814.homeAutomation.services.deviceControllers.common.JsonConverter;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.device.DeviceInfoParams;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.TapoEncryption;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.entity.EncryptedParams;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.entity.TapoKeyPair;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.handshake.HandshakeParams;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.handshake.HandshakeRequest;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.requests.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TapoClient {
    private static final Logger logger = LogManager.getLogger(TapoClient.class);
    private final TapoHttpClient tapoHttpClient;
    private String endpoint;
    private String authEndpoint;

    public TapoClient(TapoHttpClient tapoHttpClient) {
        this.tapoHttpClient = tapoHttpClient;
    }

    public void setIpAddress(String ipAddress) {
        this.endpoint = "http://" + ipAddress + "/app";
    }

    public void setAuthEndpoint(String token) {
        this.authEndpoint = this.endpoint + "?token=" + token;
        logger.info("authEndpoint is: " + this.authEndpoint);
    }

    /**
     * Given a generated keyPair performs a handshake with the device
     *
     * @param tapoKeyPair - Generated keypair
     * @return - response with session id and key for decoding messages
     */
    public ResponseEntity<TapoResponse> makeHandshake(TapoKeyPair tapoKeyPair) {
        HandshakeParams handshakeParams = new HandshakeParams();
        handshakeParams.setKey(tapoKeyPair.getPublicKey());
        HandshakeRequest handshakeRequest = new HandshakeRequest("handshake", handshakeParams);

        try {
            logger.info("Making handshake to " + endpoint);
            logger.info("Sending Handshake with body: " + JsonConverter.convertToString(handshakeRequest));

            ResponseEntity<TapoResponse> response = this.tapoHttpClient.tapoPost(endpoint, handshakeRequest, "null");
            logger.info("Server responded with: " + JsonConverter.convertToString(response.getBody()));
            return response;

        } catch (Exception ex) {
            logger.info("Something went wrong: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Logs in with the provided credentials, decrypter and cookie. Will return a token used
     * for authenticated requests
     */
    public String loginRequest(String username, String password, C658a c658a, String cookie) {
        try {
            logger.info("Logging in with provided credentials");
            TapoLoginRequest tapoLoginRequest = new TapoLoginRequest(
                    B64Converter.encodeToString(TapoEncryption.shaDigestUsername(username).getBytes()),
                    B64Converter.encodeToString(password.getBytes())
            );

            TapoRequest tapoRequest = new TapoRequest("login_device", tapoLoginRequest, 0);

            String decryptedResponse = postSecurePassthrough(tapoRequest, c658a, cookie, false);

            return JsonConverter.convertToJson(decryptedResponse).get("result")
                    .getAsJsonObject()
                    .get("token")
                    .toString()
                    .replace("\"", "");

        } catch (Exception e) {
            logger.info("Something went wrong: " + e.getMessage());
            return null;
        }
    }

    /**
     * Sets a tapoBulb state - including on/off and brightness from DeviceInfoParams
     *
     * @param c658a            - decryption class specific to session
     * @param cookie           - session id
     * @param deviceInfoParams - JSON object containing instructions to modify bulb state
     */
    public void setBulbState(C658a c658a, String cookie, DeviceInfoParams deviceInfoParams) {
        try {

            TapoSetDeviceRequest tapoRequest = new TapoSetDeviceRequest("set_device_info", deviceInfoParams, System.currentTimeMillis());
            tapoRequest.setTerminalUUID("88-54-DE-AD-52-E1"); //TODO Set to mac address of device

            String getMe = JsonConverter.convertToString(tapoRequest);
            logger.info("Unencrypted request content: " + getMe);

            postSecurePassthrough(tapoRequest, c658a, cookie, true);
        } catch (Exception ex) {
            logger.error("Something went wrong! " + ex.getMessage());
        }
    }

    /**
     * Gets state of bulb in JSON Format
     */
    public String getState(C658a c658a, String cookie) {
        try {
            DeviceInfoParams deviceInfoParams = new DeviceInfoParams();

            TapoSetDeviceRequest tapoRequest = new TapoSetDeviceRequest("get_device_info", deviceInfoParams, System.currentTimeMillis());
            tapoRequest.setTerminalUUID("88-54-DE-AD-52-E1");

            String decryptedResponse = postSecurePassthrough(tapoRequest, c658a, cookie, true);
            logger.info("Decrypted: " + decryptedResponse);

            return decryptedResponse;

        } catch (Exception ex) {
            logger.error("Something went wrong! " + ex.getMessage());
        }
        return null;
    }

    /**
     * Given a request, encrypt and parse into a secure passthrough request ready for receiving
     * from the tapo device
     *
     * @param tapoRequest - Json request that will be secured and encrypted via the c658a encryptor
     * @param c658a       - encryptor
     * @param cookie      - session id
     * @param auth        - whether to use the auth end point or regular
     * @return decrypted api response
     * @throws Exception - bare exception - needs to be updated
     */
    public String postSecurePassthrough(Object tapoRequest, C658a c658a, String cookie, boolean auth) throws Exception {
        endpoint = (auth ? this.authEndpoint : this.endpoint);
        String getMe = JsonConverter.convertToString(tapoRequest);
        logger.info("Unencrypted request content: " + getMe);

        logger.info("Encrypting request content...");
        String encrypted = c658a.mo38009b_enc(getMe);

        EncryptedParams encryptedParams = new EncryptedParams(encrypted);
        SecurePassthroughRequest securePassthroughRequest = new SecurePassthroughRequest("securePassthrough", encryptedParams);

        logger.info("request Body " + JsonConverter.convertToString(securePassthroughRequest));

        logger.info("Sending request to " + endpoint);
        ResponseEntity<TapoResponse> response = this.tapoHttpClient.tapoPost(endpoint, securePassthroughRequest, cookie);

        logger.info("Server responsed with (encrypted): " + JsonConverter.convertToString(response.getBody()));

        String encryptedResponse = response.getBody().getResult().get("response");
        String decryptedResponse = c658a.mo38006a_dec(encryptedResponse);
        logger.info("Decrypted response: " + decryptedResponse);

        return decryptedResponse;
    }


}
