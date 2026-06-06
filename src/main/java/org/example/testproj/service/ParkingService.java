package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.client.UjinClient;
import org.example.testproj.dto.ParkingStatsDto;
import org.example.testproj.dto.ujin.UjinParkingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final UjinClient ujinClient;

    public List<ParkingStatsDto> getStats(Integer complexId) {
        UjinParkingResponse response = ujinClient.getParkingList(toList(complexId), null);
        if (response.getData() == null || response.getData().getItems() == null) return List.of();

        return response.getData().getItems().stream()
                .map(complex -> ParkingStatsDto.builder()
                        .complexId(complex.getComplexId())
                        .complexTitle(complex.getComplexTitle())
                        .buildings(complex.getBuildings().stream()
                                .map(b -> {
                                    List<UjinParkingResponse.Spot> spots = b.getZones() == null ? List.of() :
                                            b.getZones().stream()
                                            .filter(z -> z.getSpots() != null)
                                            .flatMap(z -> z.getSpots().stream())
                                            .toList();
                                    int free = (int) spots.stream().filter(s -> "free".equals(s.getStatus())).count();
                                    int occupied = (int) spots.stream().filter(s -> "occupied".equals(s.getStatus())).count();
                                    return ParkingStatsDto.BuildingStats.builder()
                                            .buildingId(b.getBuildingId())
                                            .buildingTitle(b.getBuildingTitle())
                                            .free(free)
                                            .occupied(occupied)
                                            .total(spots.size())
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
