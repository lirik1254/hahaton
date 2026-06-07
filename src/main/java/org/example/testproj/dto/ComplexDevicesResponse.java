package org.example.testproj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ComplexDevicesResponse {
    private String complexId;
    private String complexName;
    private List<BuildingDevicesResponse> buildings = new ArrayList<>();
}
