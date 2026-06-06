package org.example.testproj.client;

import lombok.RequiredArgsConstructor;
import org.example.testproj.config.UjinProperties;
import org.example.testproj.dto.ujin.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UjinClient {

    private final RestClient ujinRestClient;
    private final UjinProperties ujinProperties;

    public UjinComplexListResponse getComplexList() {
        return ujinRestClient.get()
                .uri(b -> b.path("/v1/complex/list/")
                        .queryParam("token", ujinProperties.getToken())
                        .build())
                .retrieve()
                .body(UjinComplexListResponse.class);
    }

    public UjinBuildingsListResponse getBuildingsList(Integer perPage, Integer page,
                                                      Integer complexId, String search) {
        return ujinRestClient.get()
                .uri(b -> {
                    UriBuilder ub = b.path("/v1/buildings/get-list-crm/")
                            .queryParam("token", ujinProperties.getToken());
                    if (perPage != null) ub = ub.queryParam("per_page", perPage);
                    if (page != null) ub = ub.queryParam("page", page);
                    if (complexId != null) ub = ub.queryParam("complex_id", complexId);
                    if (search != null) ub = ub.queryParam("search", search);
                    return ub.build();
                })
                .retrieve()
                .body(UjinBuildingsListResponse.class);
    }

    public UjinParkingResponse getParkingList(List<Integer> complexIds, List<Integer> buildingIds) {
        return getParkingResponse("/api/v1/parking/list", complexIds, buildingIds);
    }

    public UjinParkingResponse getParkingFree(List<Integer> complexIds, List<Integer> buildingIds) {
        return getParkingResponse("/api/v1/parking/free", complexIds, buildingIds);
    }

    public UjinParkingResponse getParkingOccupied(List<Integer> complexIds, List<Integer> buildingIds) {
        return getParkingResponse("/api/v1/parking/occupied", complexIds, buildingIds);
    }

    public UjinParkingResponse getParkingPublic(List<Integer> complexIds, List<Integer> buildingIds) {
        return getParkingResponse("/api/v1/parking/public", complexIds, buildingIds);
    }

    public UjinParkingResponse getParkingPrivate(List<Integer> complexIds, List<Integer> buildingIds) {
        return getParkingResponse("/api/v1/parking/private", complexIds, buildingIds);
    }

    public UjinParkingResponse getParkingUnassigned(List<Integer> complexIds, List<Integer> buildingIds) {
        return getParkingResponse("/api/v1/parking/unassigned", complexIds, buildingIds);
    }

    public UjinStorageResponse getStorageList(List<Integer> complexIds, List<Integer> buildingIds) {
        return getStorageResponse("/api/v1/storage/list", complexIds, buildingIds);
    }

    public UjinStorageResponse getStorageFree(List<Integer> complexIds, List<Integer> buildingIds) {
        return getStorageResponse("/api/v1/storage/free", complexIds, buildingIds);
    }

    public UjinStorageResponse getStorageOccupied(List<Integer> complexIds, List<Integer> buildingIds) {
        return getStorageResponse("/api/v1/storage/occupied", complexIds, buildingIds);
    }

    public UjinStorageResponse getStoragePublic(List<Integer> complexIds, List<Integer> buildingIds) {
        return getStorageResponse("/api/v1/storage/public", complexIds, buildingIds);
    }

    public UjinStorageResponse getStoragePrivate(List<Integer> complexIds, List<Integer> buildingIds) {
        return getStorageResponse("/api/v1/storage/private", complexIds, buildingIds);
    }

    public UjinStorageResponse getStorageUnassigned(List<Integer> complexIds, List<Integer> buildingIds) {
        return getStorageResponse("/api/v1/storage/unassigned", complexIds, buildingIds);
    }

    public UjinNewsListResponse getNewsList(List<Integer> complexIds, List<Integer> buildingIds, String type) {
        return ujinRestClient.get()
                .uri(b -> {
                    UriBuilder ub = b.path("/v1/news/list")
                            .queryParam("token", ujinProperties.getToken());
                    if (complexIds != null) {
                        for (Integer id : complexIds) ub = ub.queryParam("complexes", id);
                    }
                    if (buildingIds != null) {
                        for (Integer id : buildingIds) ub = ub.queryParam("buildings", id);
                    }
                    if (type != null) ub = ub.queryParam("type", type);
                    return ub.build();
                })
                .retrieve()
                .body(UjinNewsListResponse.class);
    }

    public UjinNewsViewResponse getNewsView(Long id) {
        return ujinRestClient.get()
                .uri(b -> b.path("/v1/news/view")
                        .queryParam("token", ujinProperties.getToken())
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .body(UjinNewsViewResponse.class);
    }

    private UjinParkingResponse getParkingResponse(String path, List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinRestClient.get()
                .uri(b -> buildFilteredUri(b, path, complexIds, buildingIds))
                .retrieve()
                .body(UjinParkingResponse.class);
    }

    private UjinStorageResponse getStorageResponse(String path, List<Integer> complexIds, List<Integer> buildingIds) {
        return ujinRestClient.get()
                .uri(b -> buildFilteredUri(b, path, complexIds, buildingIds))
                .retrieve()
                .body(UjinStorageResponse.class);
    }

    private URI buildFilteredUri(UriBuilder b, String path,
                                  List<Integer> complexIds, List<Integer> buildingIds) {
        UriBuilder ub = b.path(path).queryParam("token", ujinProperties.getToken());
        if (complexIds != null) {
            for (Integer id : complexIds) ub = ub.queryParam("complexes[]", id);
        }
        if (buildingIds != null) {
            for (Integer id : buildingIds) ub = ub.queryParam("buildings[]", id);
        }
        return ub.build();
    }
}
