package com.aj2814.homeAutomation.services.deviceControllers.tapo.device;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetStateResponse {

    private final DeviceInfoParams result;
    private final String errorCode;

    @JsonCreator
    public GetStateResponse(
            @JsonProperty("error_code") String errorCode,
            @JsonProperty("result") DeviceInfoParams result
    ) {
        this.errorCode = errorCode;
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public DeviceInfoParams getResult() {
        return result;
    }
}

