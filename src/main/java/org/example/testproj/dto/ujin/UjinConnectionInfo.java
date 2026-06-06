package org.example.testproj.dto.ujin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UjinConnectionInfo {
    @JsonProperty("server_real_ip")
    private String serverRealIp;
    @JsonProperty("user_ip")
    private String userIp;
    private String token;
    private String fromdomain;
    private String worktime;
    @JsonProperty("worktime_tmp")
    private String worktimeTmp;
    @JsonProperty("request_uuid")
    private String requestUuid;
}
