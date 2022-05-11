package com.aj2814.homeAutomation.services.deviceControllers.tapo.handshake;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class HandshakeRequest extends JSONObject {
    private final String method;
    private final HandshakeParams params;

    @JsonCreator
    public HandshakeRequest(
            @JsonProperty("method") String method,
            @JsonProperty("params") HandshakeParams params
    ) {
        this.method = method;
        this.params = params;
    }

    public Object getParams() {
        return params;
    }

    public String getMethod() {
        return method;
    }
}