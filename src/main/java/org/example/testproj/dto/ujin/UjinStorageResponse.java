package org.example.testproj.dto.ujin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UjinStorageResponse {
    private String command;
    private String message;
    private Integer error;
    private Data data;
    private UjinConnectionInfo connection;

    @lombok.Data
    public static class Data {
        private List<StorageComplex> items;
    }

    @lombok.Data
    public static class StorageComplex {
        @JsonProperty("complex_id")
        private Integer complexId;
        @JsonProperty("complex_title")
        private String complexTitle;
        private List<StorageBuilding> buildings;
    }

    @lombok.Data
    public static class StorageBuilding {
        @JsonProperty("building_id")
        private Integer buildingId;
        @JsonProperty("building_title")
        private String buildingTitle;
        @JsonProperty("storages")
        private List<Room> rooms;
    }

    @lombok.Data
    public static class Room {
        private String id;
        @JsonProperty("assignment_type")
        private String assignmentType;
        private String status;
    }
}
