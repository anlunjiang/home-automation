package com.aj2814.homeAutomation.services.deviceRegistry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @RequestMapping("/devices")
    public RedirectView listDevices() {

        return new RedirectView("/actuator");
    }
}
