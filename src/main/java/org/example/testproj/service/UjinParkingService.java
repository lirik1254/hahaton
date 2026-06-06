package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.ujin.UjinParkingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UjinParkingService {

    private final UjinClient ujinClient;

    public UjinParkingResponse getList(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getParkingList(complexIds, buildingIds);
    }

    public UjinParkingResponse getFree(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getParkingFree(complexIds, buildingIds);
    }

    public UjinParkingResponse getOccupied(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getParkingOccupied(complexIds, buildingIds);
    }

    public UjinParkingResponse getPublic(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getParkingPublic(complexIds, buildingIds);
    }

    public UjinParkingResponse getPrivate(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getParkingPrivate(complexIds, buildingIds);
    }

    public UjinParkingResponse getUnassigned(List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinClient.getParkingUnassigned(complexIds, buildingIds);
    }
}
