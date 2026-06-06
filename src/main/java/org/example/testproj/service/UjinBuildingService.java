package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.ujin.UjinBuildingsListResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UjinBuildingService {

    private final UjinClient ujinClient;

    public UjinBuildingsListResponse getBuildingsList(Integer perPage, Integer page,
                                                      Integer complexId, String search) {
        return ujinClient.getBuildingsList(perPage, page, complexId, search);
    }
}
