package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceItemResponse;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.entity.Device;
import org.example.testproj.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceItemResponse> getDevices() {
        return deviceRepository.findAll().stream()
                .map(device -> {
                    DeviceItemResponse item = new DeviceItemResponse();
                    item.setId(device.getId().toString());
                    item.setName(device.getName());
                    return item;
                })
                .toList();
    }

    public DeviceResponse createDevice(CreateDeviceRequest request) {
        Device device = new Device();
        device.setName(request.getName());
        device.setBuildingId(request.getBuildingId());
        device.setComplexId(request.getComplexId());
        deviceRepository.save(device);

        return DeviceResponse.builder()
                .id(device.getId())
                .name(device.getName())
                .buildingId(device.getBuildingId())
                .complexId(device.getComplexId())
                .build();
    }
}
