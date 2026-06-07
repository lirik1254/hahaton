package org.example.testproj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateGroupRequest {

    @NotBlank
    private String name;

    private UUID templateId;

    @NotEmpty
    private List<String> deviceIds;
}
