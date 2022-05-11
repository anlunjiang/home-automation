package com.aj2814.homeAutomation.services.deviceControllers.tapo.device;

import java.util.HashMap;

/**
 * Default states for device info params,
 * bean for json deserialisation
 */
public class DefaultStates {
    private String type;
    private HashMap<String, Integer> state;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Integer> getState() {
        return state;
    }

    public void setState(HashMap<String, Integer> state) {
        this.state = state;
    }
}
