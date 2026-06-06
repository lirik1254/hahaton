package org.example.testproj.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TemplateResponse {

    private UUID id;
    private String name;
    private List<Widget> widgets;
}
