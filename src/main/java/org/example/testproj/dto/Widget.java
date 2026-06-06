package org.example.testproj.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Widget {

    private String name;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
}
