package com.aj2814.homeAutomation.services.deviceRegistry.controller;

import com.aj2814.homeAutomation.services.deviceRegistry.entity.DeviceEntity;
import com.aj2814.homeAutomation.services.deviceRegistry.entity.RoomEntity;
import com.aj2814.homeAutomation.services.deviceRegistry.repository.DevicesRepository;
import com.aj2814.homeAutomation.services.deviceRegistry.repository.RoomsRepository;
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

    private final DevicesRepository devicesRepository;
    private final RoomsRepository roomsRepository;

    public ApiController(DevicesRepository repository, RoomsRepository roomsRepository) {
        this.devicesRepository = repository;
        this.roomsRepository = roomsRepository;
    }

    @GetMapping("/devices")
    public ResponseEntity<List<DeviceEntity>> listDevices() {
        Iterable<DeviceEntity> devices = this.devicesRepository.findAll();
        List<DeviceEntity> devicesList =  StreamSupport.stream(devices.spliterator(), true)
                .collect(Collectors.toList());
        return new ResponseEntity<>(devicesList, HttpStatus.OK);
    }

    @PostMapping("/devices")
    public ResponseEntity<DeviceEntity> postDevice(@RequestBody DeviceEntity newDevice) {
        DeviceEntity created = devicesRepository.save(newDevice);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/devices/{deviceId}")
    public ResponseEntity<DeviceEntity> getDevice(@PathVariable int deviceId) {
        DeviceEntity device = this.devicesRepository.findByIdentifier(deviceId);
        if (device == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Device ID not found");
        }
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<RoomEntity> deleteDevice(@PathVariable int deviceId) {
        try {
            devicesRepository.deleteById(deviceId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Device ID not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomEntity>> getRooms() {
        Iterable<RoomEntity> rooms = roomsRepository.findAll();
        List<RoomEntity> roomsList =  StreamSupport.stream(rooms.spliterator(), true)
                .collect(Collectors.toList());
        return new ResponseEntity<>(roomsList, HttpStatus.OK);
    }

    @PostMapping("/rooms/")
    public ResponseEntity<RoomEntity> postRoom(@RequestBody RoomEntity newRoom) {
        RoomEntity created = this.roomsRepository.save(newRoom);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<RoomEntity> getRoom(@PathVariable int roomId) {
        RoomEntity room = this.roomsRepository.findByIdentifier(roomId);
        if (room == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room ID not found");
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<RoomEntity> deleteRoom(@PathVariable int roomId) {
        try {
            roomsRepository.deleteById(roomId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room ID not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


