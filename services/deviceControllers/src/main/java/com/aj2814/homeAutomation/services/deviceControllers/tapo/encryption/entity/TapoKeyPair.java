package com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.entity;

public class TapoKeyPair {

    private final String privateKey;
    private final String publicKey;

    public TapoKeyPair(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
