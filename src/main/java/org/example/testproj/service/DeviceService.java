package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceItemResponse;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.entity.Device;
import org.example.testproj.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceItemResponse> getDevices() {
        return toItems(deviceRepository.findAll());
    }

    public List<DeviceItemResponse> getDevicesByComplexId(Integer complexId) {
        return toItems(deviceRepository.findAllByComplexId(complexId));
    }

    public List<DeviceItemResponse> getDevicesByBuildingId(Integer buildingId) {
        return toItems(deviceRepository.findAllByBuildingId(buildingId));
    }

    public List<DeviceItemResponse> getDevicesByTemplateId(UUID templateId) {
        return toItems(deviceRepository.findAllByTemplateId(templateId));
    }

    private List<DeviceItemResponse> toItems(List<Device> devices) {
        return devices.stream()
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
