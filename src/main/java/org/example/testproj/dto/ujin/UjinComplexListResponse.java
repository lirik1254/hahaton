package org.example.testproj.dto.ujin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UjinComplexListResponse {
    private String command;
    private String message;
    private Integer error;
    private Data data;

    @lombok.Data
    public static class Data {
        private List<ComplexItem> items;
        private UjinPaginationLinks links;
        private UjinPaginationMeta meta;
    }

    @lombok.Data
    public static class ComplexItem {
        private Integer id;
        private String title;
        private Region region;
        @JsonProperty("paid_tickets_enabled")
        private Boolean paidTicketsEnabled;
    }

    @lombok.Data
    public static class Region {
        private Integer id;
        private String title;
    }
}
