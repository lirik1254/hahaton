package org.example.testproj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateGroupRequest;
import org.example.testproj.dto.GroupResponse;
import org.example.testproj.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@Valid @RequestBody CreateGroupRequest request) {
        return ResponseEntity.ok(groupService.createGroup(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGroup(@PathVariable UUID id) {
        if (!groupService.deleteGroup(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("success", true));
    }
}
