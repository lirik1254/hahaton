package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.config.UjinProperties;
import org.example.testproj.dto.BuildingDevicesResponse;
import org.example.testproj.dto.ComplexDevicesResponse;
import org.example.testproj.dto.CreateDeviceRequest;
import org.example.testproj.dto.DeviceItemResponse;
import org.example.testproj.dto.DeviceResponse;
import org.example.testproj.dto.ujin.UjinBuildingsListResponse;
import org.example.testproj.entity.Device;
import org.example.testproj.entity.Group;
import org.example.testproj.entity.Template;
import org.example.testproj.repository.DeviceRepository;
import org.example.testproj.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final TemplateRepository templateRepository;
    private final UjinClient ujinClient;
    private final UjinProperties ujinProperties;

    public List<ComplexDevicesResponse> getDevices() {
        List<Device> devices = deviceRepository.findAll();
        int pageSize = ujinProperties.getPageSize();

        Map<Integer, ComplexDevicesResponse> complexes = new LinkedHashMap<>();
        Map<Integer, BuildingDevicesResponse> buildings = new LinkedHashMap<>();

        for (Device device : devices) {
            UjinBuildingsListResponse.BuildingItem match = findBuilding(device, pageSize);

            String complexName = match != null ? match.getComplex().getTitle() : null;
            String buildingName = match != null ? match.getBuilding().getTitle() : null;

            ComplexDevicesResponse complex = complexes.computeIfAbsent(device.getComplexId(), id -> {
                ComplexDevicesResponse c = new ComplexDevicesResponse();
                c.setComplexId(id.toString());
                c.setComplexName(complexName);
                return c;
            });

            BuildingDevicesResponse building = buildings.computeIfAbsent(device.getBuildingId(), id -> {
                BuildingDevicesResponse b = new BuildingDevicesResponse();
                b.setBuildingId(id.toString());
                b.setBuildingName(buildingName);
                complex.getBuildings().add(b);
                return b;
            });

            DeviceItemResponse item = new DeviceItemResponse();
            item.setDeviceId(device.getId().toString());
            item.setDeviceName(device.getName());
            item.setDeviceTemplate(resolveTemplateName(device));
            building.getDevices().add(item);
        }

        return new ArrayList<>(complexes.values());
    }

    private UjinBuildingsListResponse.BuildingItem findBuilding(Device device, int pageSize) {
        int page = 1;
        UjinBuildingsListResponse buildingsList = ujinClient.getBuildingsList(pageSize, page, device.getComplexId(), null);
        while (true) {
            for (UjinBuildingsListResponse.BuildingItem buildingItem : buildingsList.getData().getBuildings()) {
                if (buildingItem.getBuilding().getId().equals(device.getBuildingId())) {
                    return buildingItem;
                }
            }
            if (buildingsList.getData().getMeta().getLastPage().equals(buildingsList.getData().getMeta().getCurrentPage())) {
                return null;
            }
            page = page + 1;
            buildingsList = ujinClient.getBuildingsList(pageSize, page, device.getComplexId(), null);
        }
    }

    private String resolveTemplateName(Device device) {
        return device.getGroups().stream()
                .map(Group::getTemplateId)
                .filter(Objects::nonNull)
                .findFirst()
                .flatMap(templateRepository::findById)
                .map(Template::getName)
                .orElse(null);
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
