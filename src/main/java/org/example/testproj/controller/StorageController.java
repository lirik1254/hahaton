package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.StorageStatsDto;
import org.example.testproj.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping("/stats")
    public ResponseEntity<List<StorageStatsDto>> getStats(
            @RequestParam(required = false) Integer complexId) {
        return ResponseEntity.ok(storageService.getStats(complexId));
    }
}
