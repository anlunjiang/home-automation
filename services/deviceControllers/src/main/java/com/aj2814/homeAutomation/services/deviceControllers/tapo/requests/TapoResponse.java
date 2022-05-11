package com.aj2814.homeAutomation.services.deviceControllers.tapo.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class TapoResponse {
    private final String errorCode;
    private final HashMap<String, String> result;

    @JsonCreator
    public TapoResponse(
            @JsonProperty("error_code") String errorCode,
            @JsonProperty("result") HashMap<String, String> result
    ) {
        this.errorCode = errorCode;
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HashMap<String, String> getResult() {
        return result;
    }
}