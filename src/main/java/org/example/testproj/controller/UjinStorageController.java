package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.ujin.UjinStorageResponse;
import org.example.testproj.service.UjinStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ujin/storage")
@RequiredArgsConstructor
public class UjinStorageController {

    private final UjinStorageService ujinStorageService;

    @GetMapping("/list")
    public ResponseEntity<UjinStorageResponse> getList(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinStorageService.getList(complexIds, buildingIds));
    }

    @GetMapping("/free")
    public ResponseEntity<UjinStorageResponse> getFree(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinStorageService.getFree(complexIds, buildingIds));
    }

    @GetMapping("/occupied")
    public ResponseEntity<UjinStorageResponse> getOccupied(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinStorageService.getOccupied(complexIds, buildingIds));
    }

    @GetMapping("/public")
    public ResponseEntity<UjinStorageResponse> getPublic(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinStorageService.getPublic(complexIds, buildingIds));
    }

    @GetMapping("/private")
    public ResponseEntity<UjinStorageResponse> getPrivate(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinStorageService.getPrivate(complexIds, buildingIds));
    }

    @GetMapping("/unassigned")
    public ResponseEntity<UjinStorageResponse> getUnassigned(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinStorageService.getUnassigned(complexIds, buildingIds));
    }
}
