package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.NewsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final UjinClient ujinClient;

    public List<NewsDto> getNews(Integer complexId, Integer buildingId) {
        var response = ujinClient.getNewsList(
                complexId != null ? List.of(complexId) : null,
                buildingId != null ? List.of(buildingId) : null,
                null);

        if (response.getData() == null || response.getData().getItems() == null) return List.of();

        return response.getData().getItems().stream()
                .map(item -> NewsDto.builder()
                        .id(item.getId())
                        .title(item.getTitle())
                        .date(item.getDate())
                        .text(item.getText())
                        .buildings(item.getBuildings() == null ? List.of() : item.getBuildings().stream()
                                .map(b -> NewsDto.BuildingRef.builder()
                                        .id(b.getId())
                                        .title(b.getTitle())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }
}
