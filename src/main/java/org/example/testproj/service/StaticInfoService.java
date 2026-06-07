package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.StaticInfoRequest;
import org.example.testproj.dto.StaticInfoResponse;
import org.example.testproj.entity.StaticInfo;
import org.example.testproj.repository.StaticInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaticInfoService {

    private final StaticInfoRepository staticInfoRepository;

    public List<StaticInfoResponse> getAll() {
        return staticInfoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<StaticInfoResponse> getById(UUID id) {
        return staticInfoRepository.findById(id).map(this::toResponse);
    }

    @Transactional
    public StaticInfoResponse create(StaticInfoRequest request) {
        StaticInfo entity = new StaticInfo();
        entity.setComplexId(request.getComplexId());
        entity.setMessage(request.getMessage());
        staticInfoRepository.save(entity);
        return toResponse(entity);
    }

    @Transactional
    public Optional<StaticInfoResponse> update(UUID id, StaticInfoRequest request) {
        return staticInfoRepository.findById(id).map(entity -> {
            entity.setComplexId(request.getComplexId());
            entity.setMessage(request.getMessage());
            return toResponse(entity);
        });
    }

    public boolean delete(UUID id) {
        if (!staticInfoRepository.existsById(id)) {
            return false;
        }
        staticInfoRepository.deleteById(id);
        return true;
    }

    private StaticInfoResponse toResponse(StaticInfo entity) {
        return StaticInfoResponse.builder()
                .id(entity.getId())
                .complexId(entity.getComplexId())
                .message(entity.getMessage())
                .build();
    }
}
