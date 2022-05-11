package com.aj2814.homeAutomation.services.deviceControllers.tapo.requests;

import com.aj2814.homeAutomation.services.deviceControllers.tapo.device.DeviceInfoParams;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TapoSetDeviceRequest {

    private final String method;
    private final DeviceInfoParams params;
    public final long requestTimeMils;
    private transient long requestID;
    private String terminalUUID;

    @JsonCreator
    public TapoSetDeviceRequest(
            @JsonProperty("method") String method,
            @JsonProperty("params") DeviceInfoParams params,
            @JsonProperty("requestTimeMils") long requestTimeMils
    ) {
        this.method = method;
        this.params = params;
        this.requestTimeMils = requestTimeMils;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public String getTerminalUUID() {
        return terminalUUID;
    }

    public void setTerminalUUID(String terminalUUID) {
        this.terminalUUID = terminalUUID;
    }

    public String getMethod() {
        return method;
    }

    public DeviceInfoParams getParams() {
        return params;
    }

    public long getRequestTimeMils() {
        return requestTimeMils;
    }
}
