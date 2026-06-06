package org.example.testproj.dto.ujin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UjinPaginationMeta {
    @JsonProperty("current_page")
    private Integer currentPage;
    private Integer from;
    @JsonProperty("last_page")
    private Integer lastPage;
    private String path;
    @JsonProperty("per_page")
    private Integer perPage;
    private Integer to;
    private Integer total;
}
