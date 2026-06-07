package org.example.testproj.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateDeviceRequest {

    @NotNull
    private String name;

    @NotNull
    private Integer buildingId;

    @NotNull
    private Integer complexId;
}
