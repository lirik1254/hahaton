package org.example.testproj.repository;

import org.example.testproj.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WidgetRepository extends JpaRepository<Widget, String> {

    List<Widget> findAllByNameIn(List<String> names);
}
