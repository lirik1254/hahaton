package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.StorageStatsDto;
import org.example.testproj.dto.ujin.UjinStorageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final UjinClient ujinClient;

    public List<StorageStatsDto> getStats(Integer complexId) {
        UjinStorageResponse response = ujinClient.getStorageList(toList(complexId), null);
        if (response.getData() == null || response.getData().getItems() == null) return List.of();

        return response.getData().getItems().stream()
                .map(complex -> StorageStatsDto.builder()
                        .complexId(complex.getComplexId())
                        .complexTitle(complex.getComplexTitle())
                        .buildings(complex.getBuildings().stream()
                                .map(b -> {
                                    List<UjinStorageResponse.Room> rooms = b.getRooms() != null ? b.getRooms() : List.of();
                                    int free = (int) rooms.stream().filter(r -> "free".equals(r.getStatus())).count();
                                    int occupied = (int) rooms.stream().filter(r -> "occupied".equals(r.getStatus())).count();
                                    return StorageStatsDto.BuildingStats.builder()
                                            .buildingId(b.getBuildingId())
                                            .buildingTitle(b.getBuildingTitle())
                                            .free(free)
                                            .occupied(occupied)
                                            .total(rooms.size())
                                            .build();
                                })
                                .toList())
                        .build())
                .toList();
    }

    private List<Integer> toList(Integer id) {
        return id != null ? List.of(id) : null;
    }
}
