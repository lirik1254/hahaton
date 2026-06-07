package org.example.testproj.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "static_info")
@Getter
@Setter
@NoArgsConstructor
public class StaticInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "complex_id", nullable = false)
    private Integer complexId;

    @Column(nullable = false)
    private String message;
}
