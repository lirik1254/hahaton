package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.ParkingStatsDto;
import org.example.testproj.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping("/stats")
    public ResponseEntity<List<ParkingStatsDto>> getStats(
            @RequestParam(required = false) Integer complexId) {
        return ResponseEntity.ok(parkingService.getStats(complexId));
    }
}
