package org.example.testproj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class GroupResponse {

    private Long id;
    private String name;
    private List<String> deviceIds;
}
