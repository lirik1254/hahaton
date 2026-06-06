package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.ujin.UjinComplexListResponse;
import org.example.testproj.service.UjinComplexService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ujin/complexes")
@RequiredArgsConstructor
public class UjinComplexController {

    private final UjinComplexService ujinComplexService;

    @GetMapping
    public ResponseEntity<UjinComplexListResponse> getComplexList() {
        return ResponseEntity.ok(ujinComplexService.getComplexList());
    }
}
