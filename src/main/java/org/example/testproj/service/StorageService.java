package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.StorageStatsDto;
import org.example.testproj.dto.ujin.UjinStorageResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final UjinClient ujinClient;

    public List<StorageStatsDto> getStats(Integer complexId) {
        List<Integer> ids = toList(complexId);

        Map<Integer, Integer> freeByBuilding = countRoomsByBuilding(ujinClient.getStorageFree(ids, null));
        Map<Integer, Integer> occupiedByBuilding = countRoomsByBuilding(ujinClient.getStorageOccupied(ids, null));

        UjinStorageResponse listResponse = ujinClient.getStorageList(ids, null);
        if (listResponse.getData() == null || listResponse.getData().getItems() == null) return List.of();

        return listResponse.getData().getItems().stream()
                .map(complex -> StorageStatsDto.builder()
                        .complexId(complex.getComplexId())
                        .complexTitle(complex.getComplexTitle())
                        .buildings(complex.getBuildings().stream()
                                .map(b -> {
                                    int free = freeByBuilding.getOrDefault(b.getBuildingId(), 0);
                                    int occupied = occupiedByBuilding.getOrDefault(b.getBuildingId(), 0);
                                    return StorageStatsDto.BuildingStats.builder()
                                            .buildingId(b.getBuildingId())
                                            .buildingTitle(b.getBuildingTitle())
                                            .free(free)
                                            .occupied(occupied)
                                            .total(free + occupied)
                                            .build();
                                })
                                .toList())
                        .build())
                .toList();
    }

    private Map<Integer, Integer> countRoomsByBuilding(UjinStorageResponse response) {
        if (response.getData() == null || response.getData().getItems() == null) return Map.of();
        return response.getData().getItems().stream()
                .flatMap(c -> c.getBuildings().stream())
                .collect(Collectors.toMap(
                        UjinStorageResponse.StorageBuilding::getBuildingId,
                        b -> b.getRooms() != null ? b.getRooms().size() : 0,
                        Integer::sum
                ));
    }

    private List<Integer> toList(Integer id) {
        return id != null ? List.of(id) : null;
    }
}
