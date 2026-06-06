package org.example.testproj.dto.ujin;

import lombok.Data;

import java.util.List;

@Data
public class UjinNewsListResponse {
    private String command;
    private String message;
    private Integer error;
    private Data data;
    private UjinConnectionInfo connection;

    @lombok.Data
    public static class Data {
        private List<NewsItem> items;
        private UjinPaginationLinks links;
        private UjinPaginationMeta meta;
    }

    @lombok.Data
    public static class NewsItem {
        private Integer id;
        private String title;
        private String date;
        private String text;
        private NewsType type;
        private List<NewsBuilding> buildings;
    }

    @lombok.Data
    public static class NewsType {
        private String slug;
        private String title;
    }

    @lombok.Data
    public static class NewsBuilding {
        private Integer id;
        private String title;
    }
}
