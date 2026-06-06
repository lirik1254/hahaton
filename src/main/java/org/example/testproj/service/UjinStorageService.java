package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.ujin.UjinStorageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UjinStorageService {

    private final UjinClient ujinClient;

    public UjinStorageResponse getList(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getStorageList(complexIds, buildingIds);
    }

    public UjinStorageResponse getFree(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getStorageFree(complexIds, buildingIds);
    }

    public UjinStorageResponse getOccupied(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getStorageOccupied(complexIds, buildingIds);
    }

    public UjinStorageResponse getPublic(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getStoragePublic(complexIds, buildingIds);
    }

    public UjinStorageResponse getPrivate(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getStoragePrivate(complexIds, buildingIds);
    }

    public UjinStorageResponse getUnassigned(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getStorageUnassigned(complexIds, buildingIds);
    }
}
