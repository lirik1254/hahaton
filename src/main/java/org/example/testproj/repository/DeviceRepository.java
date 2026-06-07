package org.example.testproj.repository;

import org.example.testproj.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    List<Device> findAllByIdIn(List<UUID> ids);

    List<Device> findAllByComplexId(Integer complexId);

    List<Device> findAllByBuildingId(Integer buildingId);

    @Query("select distinct d from Device d join d.groups g where g.templateId = :templateId")
    List<Device> findAllByTemplateId(@Param("templateId") UUID templateId);
}
