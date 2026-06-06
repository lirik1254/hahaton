package org.example.testproj.dto.ujin;

import lombok.Data;

import java.util.List;

@Data
public class UjinNewsViewResponse {
    private String command;
    private String message;
    private Integer error;
    private Data data;
    private UjinConnectionInfo connection;

    @lombok.Data
    public static class Data {
        private NewsDetail item;
    }

    @lombok.Data
    public static class NewsDetail {
        private Integer id;
        private String title;
        private String text;
        private String date;
        private List<String> images;
    }
}
