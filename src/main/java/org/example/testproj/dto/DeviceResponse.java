package org.example.testproj.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DeviceResponse {

    private UUID id;
    private String name;
    private Integer buildingId;
    private Integer complexId;
}
