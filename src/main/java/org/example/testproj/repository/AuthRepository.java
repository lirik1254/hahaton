package org.example.testproj.repository;

import org.example.testproj.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {
    boolean existsByLogin(String login);
}
