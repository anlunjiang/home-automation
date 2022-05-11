package com.aj2814.homeAutomation.services.deviceControllers.tapo.session;

import com.aj2814.homeAutomation.services.deviceControllers.common.C658a;
import com.aj2814.homeAutomation.services.deviceControllers.common.SetupRunConfig;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.TapoClient;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.TapoEncryption;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.entity.TapoKeyPair;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.requests.TapoResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashMap;

/**
 * Session factory - every instantiation will generate a key pair and create
 * a new session with the device configured within the tapo client
 * Once handhsake is complete the session authenticates by logging in before
 * returning the session details
 */
public class SessionMaker {

    private final TapoClient tapoClient;
    private final TapoKeyPair tapoKeyPair;
    private final HashMap<String, String> prop;

    public SessionMaker(TapoClient tapoClient) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        this.tapoClient = tapoClient;
        this.tapoKeyPair = TapoEncryption.generateKeyPair();
        this.prop = new SetupRunConfig().getProperties();
        this.tapoClient.setIpAddress(prop.get("ipAddress"));
    }

    /**
     * Handshake with device and logs in once cookie is obtained. Auth endpoint
     * is set within the client before returning a new session object
     * @return Session object
     */
    public Session getSession() {

        ResponseEntity<TapoResponse> handshakeResponse = this.tapoClient.makeHandshake(this.tapoKeyPair);
        String cookie = handshakeResponse.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        String keyFromTapo = handshakeResponse.getBody().getResult().get("key");

        System.out.println("Tapo key is: " + keyFromTapo);
        System.out.println("Session cookie is: " + cookie);

        C658a c658a = TapoEncryption.decodeTapoKey(keyFromTapo, tapoKeyPair);

        String token = tapoClient.loginRequest(
                this.prop.get("tapoEmail"),
                prop.get("tapoPassword"),
                c658a,
                cookie
        );

        System.out.println("Got token: " + token);

        this.tapoClient.setAuthEndpoint(token);
        return new Session(token, cookie, c658a);
    }
}
