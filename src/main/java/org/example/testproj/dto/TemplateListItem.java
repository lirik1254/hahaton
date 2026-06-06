package org.example.testproj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class TemplateListItem {

    private Long id;
    private String name;
}
