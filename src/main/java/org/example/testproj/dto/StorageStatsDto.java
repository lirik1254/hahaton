package org.example.testproj.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StorageStatsDto {
    private Integer complexId;
    private String complexTitle;
    private List<BuildingStats> buildings;

    @Data
    @Builder
    public static class BuildingStats {
        private Integer buildingId;
        private String buildingTitle;
        private int free;
        private int occupied;
        private int total;
    }
}
