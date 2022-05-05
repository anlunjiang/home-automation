package com.aj2814.homeAutomation.services.deviceControllers.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Sets up basic config for handshake and login to authenticate ourselves
 */
public class SetupRunConfig {

    private final HashMap<String, String> properties = new HashMap<>();

    public SetupRunConfig() {
        properties.put("ipAddress", System.getenv("ipAddress"));
        properties.put("tapoEmail", System.getenv("tapoEmail"));
        properties.put("tapoPassword", System.getenv("tapoPassword"));
    }

    public HashMap<String, String> getProperties() throws IllegalStateException{
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            if (entry.getValue() == null) {
                throw new IllegalStateException("No value for environment variable " + entry.getKey());
            }
        }
        return properties;
    }
}
