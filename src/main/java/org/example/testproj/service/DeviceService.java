package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public Map<String, List<String>> getDevices() {
        return deviceRepository.findAll().stream()
                .collect(Collectors.toMap(
                        d -> d.getId().toString(),
                        d -> List.of(d.getBuildingId().toString(), d.getId().toString())
                ));
    }
}
