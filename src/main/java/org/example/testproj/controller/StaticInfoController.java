package org.example.testproj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.StaticInfoRequest;
import org.example.testproj.dto.StaticInfoResponse;
import org.example.testproj.service.StaticInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/static-info")
@RequiredArgsConstructor
public class StaticInfoController {

    private final StaticInfoService staticInfoService;

    @GetMapping
    public ResponseEntity<List<StaticInfoResponse>> getAll() {
        return ResponseEntity.ok(staticInfoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaticInfoResponse> getById(@PathVariable UUID id) {
        return staticInfoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StaticInfoResponse> create(@Valid @RequestBody StaticInfoRequest request) {
        return ResponseEntity.ok(staticInfoService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaticInfoResponse> update(@PathVariable UUID id,
                                                     @Valid @RequestBody StaticInfoRequest request) {
        return staticInfoService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable UUID id) {
        if (!staticInfoService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("success", true));
    }
}
