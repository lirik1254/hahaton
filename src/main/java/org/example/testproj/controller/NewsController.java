package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.NewsDto;
import org.example.testproj.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsDto>> getNews(
            @RequestParam(required = false) Integer complexId,
            @RequestParam(required = false) Integer buildingId) {
        return ResponseEntity.ok(newsService.getNews(complexId, buildingId));
    }
}
