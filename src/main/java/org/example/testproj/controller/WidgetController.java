package org.example.testproj.controller;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.Widget;
import org.example.testproj.service.WidgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/widgets")
@RequiredArgsConstructor
public class WidgetController {

    private final WidgetService widgetService;

    @GetMapping
    public ResponseEntity<List<Widget>> getWidgets() {
        return ResponseEntity.ok(widgetService.getWidgets());
    }
}
