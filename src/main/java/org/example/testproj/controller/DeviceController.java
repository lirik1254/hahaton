package org.example.testproj.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceItemResponse;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(
            summary = "Список устройств",
            description = "Возвращает список устройств с id и названием"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Успешный ответ",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = DeviceItemResponse.class)),
                    examples = @ExampleObject(
                            name = "Пример ответа",
                            value = """
                                    [
                                      {
                                        "id": "lsdjfljsd",
                                        "name": "Устройство1"
                                      },
                                      {
                                        "id": "a1b2c3d4",
                                        "name": "Устройство2"
                                      }
                                    ]"""
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<DeviceItemResponse>> getDevices() {
        return ResponseEntity.ok(deviceService.getDevices());
    }

    @Operation(summary = "Список устройств по ЖК", description = "Возвращает устройства, привязанные к указанному complexId")
    @GetMapping("/by-complex/{complexId}")
    public ResponseEntity<List<DeviceItemResponse>> getDevicesByComplex(@PathVariable Integer complexId) {
        return ResponseEntity.ok(deviceService.getDevicesByComplexId(complexId));
    }

    @Operation(summary = "Список устройств по строению", description = "Возвращает устройства, привязанные к указанному buildingId")
    @GetMapping("/by-building/{buildingId}")
    public ResponseEntity<List<DeviceItemResponse>> getDevicesByBuilding(@PathVariable Integer buildingId) {
        return ResponseEntity.ok(deviceService.getDevicesByBuildingId(buildingId));
    }

    @Operation(summary = "Список устройств по шаблону", description = "Возвращает устройства, чья группа привязана к указанному templateId")
    @GetMapping("/by-template/{templateId}")
    public ResponseEntity<List<DeviceItemResponse>> getDevicesByTemplate(@PathVariable UUID templateId) {
        return ResponseEntity.ok(deviceService.getDevicesByTemplateId(templateId));
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> createDevice(@Valid @RequestBody CreateDeviceRequest request) {
        return ResponseEntity.ok(deviceService.createDevice(request));
    }
}
