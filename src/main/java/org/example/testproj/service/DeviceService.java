package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.config.UjinProperties;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceAllResponse;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.dto.ujin.UjinBuildingsListResponse;
import org.example.testproj.entity.Device;
import org.example.testproj.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UjinClient ujinClient;
    private final UjinProperties ujinProperties;

    public Map<String, DeviceAllResponse> getDevices() {
        List<Device> devices = deviceRepository.findAll();
        Map<String, DeviceAllResponse> retMap = new HashMap<>();
        int page = 1;
        int pageSize = ujinProperties.getPageSize();

        for (Device device : devices) {
            DeviceAllResponse response = new DeviceAllResponse();
            UjinBuildingsListResponse buildingsList = ujinClient.getBuildingsList(pageSize, page, device.getComplexId(), null);
            while (!Objects.equals(buildingsList.getData().getMeta().getLastPage() + 1, buildingsList.getData().getMeta().getCurrentPage())) {
                buildingsList.getData().getBuildings().forEach(buildingItem -> {
                    if (buildingItem.getBuilding().getId().equals(device.getBuildingId())) {
                        response.setComplexName(buildingItem.getComplex().getTitle());
                        response.setBuildingName(buildingItem.getBuilding().getTitle());
                        retMap.put(device.getId().toString(), response);
                    }
                });
                if (buildingsList.getData().getMeta().getLastPage().equals(buildingsList.getData().getMeta().getCurrentPage())) {
                    break;
                }
                page = page + 1;
                buildingsList = ujinClient.getBuildingsList(pageSize, page, device.getComplexId(), null);
            }
            retMap.get(device.getId().toString()).setDeviceName(device.getName());
        }

        return retMap;
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
