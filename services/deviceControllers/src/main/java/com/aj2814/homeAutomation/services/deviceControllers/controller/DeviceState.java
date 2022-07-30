package com.aj2814.homeAutomation.services.deviceControllers.controller;

public enum DeviceState {
    ON(true),
    OFF(false);
    final boolean state;

    DeviceState(boolean b) {
        state = b;
    }
}
