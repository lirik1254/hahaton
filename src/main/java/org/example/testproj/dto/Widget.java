package org.example.testproj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Widget {

    private String name;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
}
