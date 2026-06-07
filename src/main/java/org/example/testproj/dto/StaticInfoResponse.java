package org.example.testproj.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class StaticInfoResponse {

    private UUID id;
    private Integer complexId;
    private String message;
}
