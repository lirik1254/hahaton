package org.example.testproj.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceAllResponse;
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

    @Operation(
            summary = "Список устройств",
            description = "Возвращает мапу, где ключ — id устройства, а значение — массив [ЖК, здание, название устройства]"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Успешный ответ",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object"),
                    examples = @ExampleObject(
                            name = "Пример ответа",
                            value = """
                                    {
                                      "id1": ["ЖК1", "Здание 1", "Настенный телевизор1-1"],
                                      "id2": ["ЖК1", "Здание 2", "Настенный телевизор1-2"],
                                      "id3": ["ЖК2", "Здание 1", "Настенный телевизор2-1"]
                                    }"""
                    )
            )
    )
    @GetMapping
    public ResponseEntity<Map<String, DeviceAllResponse>> getDevices() {
        return ResponseEntity.ok(deviceService.getDevices());
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> createDevice(@Valid @RequestBody CreateDeviceRequest request) {
        return ResponseEntity.ok(deviceService.createDevice(request));
    }
}
