package org.example.testproj.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GroupResponse {

    private UUID id;
    private String name;
    private UUID templateId;
    private List<String> deviceIds;
}
