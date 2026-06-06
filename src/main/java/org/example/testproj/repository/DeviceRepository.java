package org.example.testproj.repository;

import org.example.testproj.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    List<Device> findAllByIdIn(List<UUID> ids);
}
