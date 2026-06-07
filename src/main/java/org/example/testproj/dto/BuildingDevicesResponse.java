package org.example.testproj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BuildingDevicesResponse {
    private String buildingId;
    private String buildingName;
    private List<DeviceItemResponse> devices = new ArrayList<>();
}
