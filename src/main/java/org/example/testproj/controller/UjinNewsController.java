package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.ujin.UjinNewsListResponse;
import org.example.testproj.dto.ujin.UjinNewsViewResponse;
import org.example.testproj.service.UjinNewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ujin/news")
@RequiredArgsConstructor
public class UjinNewsController {

    private final UjinNewsService ujinNewsService;

    @GetMapping("/list")
    public ResponseEntity<UjinNewsListResponse> getNewsList(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds,
            @RequestParam(required = false) String type) {
        return ResponseEntity.ok(ujinNewsService.getNewsList(complexIds, buildingIds, type));
    }

    @GetMapping("/view")
    public ResponseEntity<UjinNewsViewResponse> getNewsView(@RequestParam Long id) {
        return ResponseEntity.ok(ujinNewsService.getNewsView(id));
    }
}
