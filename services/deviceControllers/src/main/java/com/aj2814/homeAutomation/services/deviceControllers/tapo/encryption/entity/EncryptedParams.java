package com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EncryptedParams {

    private String request;

    @JsonCreator
    public EncryptedParams(@JsonProperty("request") String request) {
        this.request = request;
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String str) {
        this.request = str;
    }

}