package org.example.testproj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceItemResponse {
    private String deviceId;
    private String deviceName;
    private String deviceTemplate;
}
