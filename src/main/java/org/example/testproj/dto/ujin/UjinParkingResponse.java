package org.example.testproj.dto.ujin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UjinParkingResponse {
    private String command;
    private String message;
    private Integer error;
    private Data data;
    private UjinConnectionInfo connection;

    @lombok.Data
    public static class Data {
        private List<ParkingComplex> items;
    }

    @lombok.Data
    public static class ParkingComplex {
        @JsonProperty("complex_id")
        private Integer complexId;
        @JsonProperty("complex_title")
        private String complexTitle;
        private List<ParkingBuilding> buildings;
    }

    @lombok.Data
    public static class ParkingBuilding {
        @JsonProperty("building_id")
        private Integer buildingId;
        @JsonProperty("building_title")
        private String buildingTitle;
        private List<Zone> zones;
    }

    @lombok.Data
    public static class Zone {
        private String id;
        private String name;
        private List<Spot> spots;
    }

    @lombok.Data
    public static class Spot {
        private String id;
        @JsonProperty("assignment_type")
        private String assignmentType;
        private String status;
    }
}
