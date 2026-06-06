package org.example.testproj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public ResponseEntity<Map<String, List<String>>> getDevices() {
        return ResponseEntity.ok(deviceService.getDevices());
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> createDevice(@Valid @RequestBody CreateDeviceRequest request) {
        return ResponseEntity.ok(deviceService.createDevice(request));
    }
}
