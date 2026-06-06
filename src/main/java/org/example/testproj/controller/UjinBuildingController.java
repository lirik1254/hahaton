package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.ujin.UjinBuildingsListResponse;
import org.example.testproj.service.UjinBuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ujin/buildings")
@RequiredArgsConstructor
public class UjinBuildingController {

    private final UjinBuildingService ujinBuildingService;

    @GetMapping
    public ResponseEntity<UjinBuildingsListResponse> getBuildingsList(
            @RequestParam(required = false) Integer perPage,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer complexId,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ujinBuildingService.getBuildingsList(perPage, page, complexId, search));
    }
}
