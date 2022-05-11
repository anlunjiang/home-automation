package com.aj2814.homeAutomation.services.deviceControllers.tapo.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TapoRequest {

    private final String method;
    private final TapoLoginRequest params;
    public final long requestTimeMils;
    private transient long requestID;
    private String terminalUUID;

    @JsonCreator
    public TapoRequest(
            @JsonProperty("method") String method,
            @JsonProperty("params") TapoLoginRequest params,
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

    public TapoLoginRequest getParams() {
        return params;
    }

    public long getRequestTimeMils() {
        return requestTimeMils;
    }
}
