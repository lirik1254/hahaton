package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.AuthRequest;
import org.example.testproj.dto.AuthResponse;
import org.example.testproj.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public boolean isNew(String login) {
        return authRepository.existsByLogin(login);
    }

//    public AuthResponse processAuth(AuthRequest authRequest) {
//
//    }

    public String retHash(String login, String password) {
        try {
            String data = login + ":" + password;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка хеширования", e);
        }
    }
}
