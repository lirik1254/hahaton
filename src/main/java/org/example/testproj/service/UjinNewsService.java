package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.ujin.UjinNewsListResponse;
import org.example.testproj.dto.ujin.UjinNewsViewResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UjinNewsService {

    private final UjinClient ujinClient;

    public UjinNewsListResponse getNewsList(List<Integer> complexIds, List<Integer> buildingIds, String type) {
        return ujinClient.getNewsList(complexIds, buildingIds, type);
    }

    public UjinNewsViewResponse getNewsView(Long id) {
        return ujinClient.getNewsView(id);
    }
}
