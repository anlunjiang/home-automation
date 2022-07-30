package com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption;

import com.aj2814.homeAutomation.services.deviceControllers.common.B64Converter;
import com.aj2814.homeAutomation.services.deviceControllers.common.C658a;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.entity.TapoKeyPair;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

public class TapoEncryption {

    public static TapoKeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
        instance.initialize(1024, new SecureRandom());
        KeyPair generateKeyPair = instance.generateKeyPair();

        String publicKey = new String(java.util.Base64.getMimeEncoder().encode(generateKeyPair.getPublic().getEncoded()));
        String privateKey = new String(java.util.Base64.getMimeEncoder().encode(generateKeyPair.getPrivate().getEncoded()));

        return new TapoKeyPair(privateKey, publicKey);
    }

    public static C658a decodeTapoKey(String key, TapoKeyPair keyPair) {
        System.out.println("Decoding key: " + key);

        try {
            byte[] decodedKey = B64Converter.decode(key.getBytes(StandardCharsets.UTF_8));
            byte[] decodedPrivateKey = B64Converter.decode(keyPair.getPrivateKey());

            Cipher cipherInstance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPrivateKey));
            cipherInstance.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] doFinal = cipherInstance.doFinal(decodedKey);
            byte[] bArr = new byte[16];
            byte[] bArr2 = new byte[16];

            System.arraycopy(doFinal, 0, bArr, 0, 16);
            System.arraycopy(doFinal, 16, bArr2, 0, 16);

            return new C658a(bArr, bArr2);

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return null;
        }
    }

    public static String shaDigestUsername(String str) throws NoSuchAlgorithmException {
        byte[] bArr = str.getBytes();
        byte[] digest = MessageDigest.getInstance("SHA1").digest(bArr);

        StringBuilder sb = new StringBuilder();
        for(byte b : digest){
            String hexString = Integer.toHexString(b & 255);
            if(hexString.length() == 1){
                sb.append("0");
                sb.append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        String a = sb.toString();
        return sb.toString();
    }
}
