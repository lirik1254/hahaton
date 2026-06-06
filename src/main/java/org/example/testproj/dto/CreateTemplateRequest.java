package org.example.testproj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateTemplateRequest {

    @NotBlank
    private String name;

    @NotNull
    private List<Widget> widgets;
}
