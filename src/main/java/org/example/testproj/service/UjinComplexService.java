package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.ujin.UjinComplexListResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UjinComplexService {

    private final UjinClient ujinClient;

    public UjinComplexListResponse getComplexList() {
        return ujinClient.getComplexList();
    }
}
