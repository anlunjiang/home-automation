package com.aj2814.homeAutomation.services.deviceRegistry.controller;

import com.aj2814.homeAutomation.services.deviceRegistry.entity.Device;
import com.aj2814.homeAutomation.services.deviceRegistry.repository.DevicesRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/devices")
    public List<Device> listDevices() {
        Iterable<Device> registry = this.repository.findAll();
        return StreamSupport.stream(registry.spliterator(), true)
                .collect(Collectors.toList());
    }

    @PostMapping("/devices")
    public ResponseEntity<Device> postDevices(@RequestBody Device newDevice) {
        System.out.println(newDevice);
        Device created = repository.save(newDevice);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/device/{deviceId}")
    @ResponseBody
    public ResponseEntity<Device> getDevice(@PathVariable int deviceId) {
        Device device = this.repository.findByIdentifier(deviceId);
        if (device == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Device ID not found");
        }
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @DeleteMapping("/device/{deviceId}")
    @ResponseBody
    public ResponseEntity<Device> deleteDevice(@PathVariable int deviceId) {
        try {
            repository.deleteById(deviceId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Device ID not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
