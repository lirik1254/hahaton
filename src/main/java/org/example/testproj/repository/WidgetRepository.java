package org.example.testproj.repository;

import org.example.testproj.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WidgetRepository extends JpaRepository<Widget, UUID> {

    List<Widget> findAllByIdIn(List<UUID> ids);
}
