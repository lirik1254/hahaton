package org.example.testproj.controller;

import jakarta.validation.Valid;
import org.example.testproj.dto.AuthRequest;
import org.example.testproj.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(new AuthResponse("klasdjflaj48alskdfjlajsdf"));
    }
}
