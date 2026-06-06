package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.entity.Device;
import org.example.testproj.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public Map<String, List<String>> getDevices() {
        return deviceRepository.findAll().stream()
                .collect(Collectors.toMap(
                        d -> d.getId().toString(),
                        d -> List.of(d.getBuildingId().toString(), d.getId().toString())
                ));
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
