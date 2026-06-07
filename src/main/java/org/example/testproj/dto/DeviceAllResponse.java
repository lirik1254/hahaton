package org.example.testproj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceAllResponse {
    private String complexName;
    private String buildingName;
    private String deviceName;
}
