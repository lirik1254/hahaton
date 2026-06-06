package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateTemplateRequest;
import org.example.testproj.dto.TemplateListItem;
import org.example.testproj.dto.TemplateResponse;
import org.example.testproj.entity.Template;
import org.example.testproj.entity.Widget;
import org.example.testproj.repository.TemplateRepository;
import org.example.testproj.repository.WidgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final WidgetRepository widgetRepository;

    public List<TemplateListItem> getTemplates() {
        return templateRepository.findAll().stream()
                .map(t -> TemplateListItem.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .build())
                .toList();
    }

    @Transactional
    public TemplateResponse createTemplate(CreateTemplateRequest request) {
        List<Widget> widgets = request.getWidgets().stream()
                .map(dto -> {
                    Widget widget = new Widget();
                    widget.setName(dto.getName());
                    widget.setX(dto.getX());
                    widget.setY(dto.getY());
                    widget.setWidth(dto.getWidth());
                    widget.setHeight(dto.getHeight());
                    return widgetRepository.save(widget);
                })
                .toList();

        Template template = new Template();
        template.setName(request.getName());
        template.setWidgets(widgets);
        templateRepository.save(template);

        return toResponse(template);
    }

    public Optional<TemplateResponse> getTemplate(UUID id) {
        return templateRepository.findById(id).map(this::toResponse);
    }

    public boolean deleteTemplate(UUID id) {
        if (!templateRepository.existsById(id)) {
            return false;
        }
        templateRepository.deleteById(id);
        return true;
    }

    private TemplateResponse toResponse(Template template) {
        List<org.example.testproj.dto.Widget> widgetDtos = template.getWidgets().stream()
                .map(w -> org.example.testproj.dto.Widget.builder()
                        .name(w.getName())
                        .x(w.getX())
                        .y(w.getY())
                        .width(w.getWidth())
                        .height(w.getHeight())
                        .build())
                .toList();

        return TemplateResponse.builder()
                .id(template.getId())
                .name(template.getName())
                .widgets(widgetDtos)
                .build();
    }
}
