package com.aj2814.homeAutomation.services.deviceRegistry.controller;

import com.aj2814.homeAutomation.services.deviceRegistry.entity.Registry;
import com.aj2814.homeAutomation.services.deviceRegistry.repository.DevicesRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final DevicesRepository repository;

    public ApiController(DevicesRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/devices")
    public List<Registry> listDevices() {
        Iterable<Registry> registry = this.repository.findAll();
        return StreamSupport.stream(registry.spliterator(), true)
                .collect(Collectors.toList());
    }
}
