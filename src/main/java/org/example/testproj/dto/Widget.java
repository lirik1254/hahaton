package org.example.testproj.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Widget {

    private UUID id;
    private String name;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
}
