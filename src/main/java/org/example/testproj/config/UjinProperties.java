package org.example.testproj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ujin")
public class UjinProperties {
    private String baseUrl;
    private String token;
}
