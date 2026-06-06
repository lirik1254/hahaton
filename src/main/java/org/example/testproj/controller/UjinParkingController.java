package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.ujin.UjinParkingResponse;
import org.example.testproj.service.UjinParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ujin/parking")
@RequiredArgsConstructor
public class UjinParkingController {

    private final UjinParkingService ujinParkingService;

    @GetMapping("/list")
    public ResponseEntity<UjinParkingResponse> getList(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinParkingService.getList(complexIds, buildingIds));
    }

    @GetMapping("/free")
    public ResponseEntity<UjinParkingResponse> getFree(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinParkingService.getFree(complexIds, buildingIds));
    }

    @GetMapping("/occupied")
    public ResponseEntity<UjinParkingResponse> getOccupied(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinParkingService.getOccupied(complexIds, buildingIds));
    }

    @GetMapping("/public")
    public ResponseEntity<UjinParkingResponse> getPublic(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinParkingService.getPublic(complexIds, buildingIds));
    }

    @GetMapping("/private")
    public ResponseEntity<UjinParkingResponse> getPrivate(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinParkingService.getPrivate(complexIds, buildingIds));
    }

    @GetMapping("/unassigned")
    public ResponseEntity<UjinParkingResponse> getUnassigned(
            @RequestParam(required = false) List<Integer> complexIds,
            @RequestParam(required = false) List<Integer> buildingIds) {
        return ResponseEntity.ok(ujinParkingService.getUnassigned(complexIds, buildingIds));
    }
}
