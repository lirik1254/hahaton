package org.example.testproj.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth")
@Getter
@Setter
@NoArgsConstructor
public class Auth {

    @Id
    @Column(length = 255)
    private String login;

    @Column(name = "hash_pass", nullable = false, length = 256)
    private String hashPass;
}
