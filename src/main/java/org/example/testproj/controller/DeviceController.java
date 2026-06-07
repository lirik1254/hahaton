package org.example.testproj.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.ComplexDevicesResponse;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(
            summary = "Список устройств",
            description = "Возвращает список ЖК; в каждом ЖК — список строений, в каждом строении — список устройств с шаблоном"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Успешный ответ",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ComplexDevicesResponse.class)),
                    examples = @ExampleObject(
                            name = "Пример ответа",
                            value = """
                                    [
                                      {
                                        "complexId": "4",
                                        "complexName": "ЖК 1",
                                        "buildings": [
                                          {
                                            "buildingId": "54",
                                            "buildingName": "Здание1",
                                            "devices": [
                                              {
                                                "deviceId": "lsdjfljsd",
                                                "deviceName": "Устройство1",
                                                "deviceTemplate": "Шаблон 1"
                                              }
                                            ]
                                          }
                                        ]
                                      }
                                    ]"""
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<ComplexDevicesResponse>> getDevices() {
        return ResponseEntity.ok(deviceService.getDevices());
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> createDevice(@Valid @RequestBody CreateDeviceRequest request) {
        return ResponseEntity.ok(deviceService.createDevice(request));
    }
}
