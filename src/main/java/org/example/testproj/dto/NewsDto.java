package org.example.testproj.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewsDto {
    private Integer id;
    private String title;
    private String date;
    private String text;
    private List<BuildingRef> buildings;

    @Data
    @Builder
    public static class BuildingRef {
        private Integer id;
        private String title;
    }
}
