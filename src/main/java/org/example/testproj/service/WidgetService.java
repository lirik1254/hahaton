package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.Widget;
import org.example.testproj.repository.WidgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WidgetService {

    private final WidgetRepository widgetRepository;

    public List<Widget> getWidgets() {
        return widgetRepository.findAll().stream()
                .map(w -> Widget.builder()
                        .name(w.getName())
                        .x(w.getX())
                        .y(w.getY())
                        .width(w.getWidth())
                        .height(w.getHeight())
                        .build())
                .toList();
    }
}
