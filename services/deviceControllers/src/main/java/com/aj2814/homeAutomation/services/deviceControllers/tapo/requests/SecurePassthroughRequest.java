package com.aj2814.homeAutomation.services.deviceControllers.tapo.requests;

import com.aj2814.homeAutomation.services.deviceControllers.tapo.encryption.entity.EncryptedParams;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class SecurePassthroughRequest extends JSONObject {
    private final String method;
    private final EncryptedParams params;

    @JsonCreator
    public SecurePassthroughRequest(
            @JsonProperty("method") String method,
            @JsonProperty("params") EncryptedParams params
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
