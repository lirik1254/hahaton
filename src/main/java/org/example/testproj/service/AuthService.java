package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.AuthRequest;
import org.example.testproj.dto.AuthResponse;
import org.example.testproj.repository.AuthRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtService jwtService;

    public AuthResponse processAuth(AuthRequest request) {
        var auth = authRepository.findById(request.getLogin())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        String hash = retHash(request.getLogin(), request.getPassword());
        if (!auth.getHashPass().equals(hash)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return new AuthResponse(jwtService.generate(request.getLogin()));
    }

    private String retHash(String login, String password) {
        try {
            String data = login + ":" + password;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка хеширования", e);
        }
    }
}
