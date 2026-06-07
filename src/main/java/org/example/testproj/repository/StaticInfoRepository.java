package org.example.testproj.repository;

import org.example.testproj.entity.StaticInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaticInfoRepository extends JpaRepository<StaticInfo, UUID> {
}
