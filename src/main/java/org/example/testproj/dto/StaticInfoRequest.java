package org.example.testproj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StaticInfoRequest {

    @NotNull
    private Integer complexId;

    @NotBlank
    private String message;
}
