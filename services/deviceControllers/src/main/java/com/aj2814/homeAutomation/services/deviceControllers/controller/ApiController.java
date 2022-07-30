package com.aj2814.homeAutomation.services.deviceControllers.controller;

import com.aj2814.homeAutomation.services.deviceControllers.tapo.TapoClient;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.device.DeviceInfoParams;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.device.GetStateResponse;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.session.Session;
import com.aj2814.homeAutomation.services.deviceControllers.tapo.session.SessionMaker;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final TapoClient tapoClient;
    private final Session session;

    public ApiController(TapoClient tapoClient) throws NoSuchAlgorithmException {
        this.tapoClient = tapoClient;
        this.session = new SessionMaker(tapoClient).getSession();
    }

    @GetMapping("/setDevice/{state}")
    public GetStateResponse setDeviceSwitch(@PathVariable String state) {

        DeviceInfoParams deviceInfoParams = new DeviceInfoParams();
        boolean enabled = DeviceState.valueOf(state.toUpperCase()).state;

        deviceInfoParams.setDevice_on(enabled);

        tapoClient.setBulbState(
                this.session.getC658a(),
                this.session.getCookie(),
                deviceInfoParams
        );

        String ret = tapoClient.getState(
                this.session.getC658a(),
                this.session.getCookie()
        );

        return new Gson().fromJson(ret, GetStateResponse.class);

    }

    @GetMapping("/setDeviceBrightness/{level}")
    public GetStateResponse setDeviceSwitch(@PathVariable Integer level) {

        DeviceInfoParams deviceInfoParams = new DeviceInfoParams();
        deviceInfoParams.setBrightness(level);

        tapoClient.setBulbState(
                this.session.getC658a(),
                this.session.getCookie(),
                deviceInfoParams
        );

        String ret = tapoClient.getState(
                this.session.getC658a(),
                this.session.getCookie()
        );

        return new Gson().fromJson(ret, GetStateResponse.class);

    }
}

