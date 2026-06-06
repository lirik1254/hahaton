package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.entity.Device;
import org.example.testproj.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public Map<String, List<String>> getDevices() {
        Map<String, List<String>> result = new LinkedHashMap<>();
        result.put("id1", List.of("ЖК1", "Здание 1", "Настенный телевизор1-1"));
        result.put("id2", List.of("ЖК1", "Здание 2", "Настенный телевизор1-2"));
        result.put("id3", List.of("ЖК2", "Здание 1", "Настенный телевизор2-1"));
        return result;
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
