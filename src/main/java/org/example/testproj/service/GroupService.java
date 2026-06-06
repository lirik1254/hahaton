package org.example.testproj.service;

import lombok.RequiredArgsConstructor;
import org.example.testproj.dto.CreateGroupRequest;
import org.example.testproj.dto.GroupResponse;
import org.example.testproj.entity.Device;
import org.example.testproj.entity.Group;
import org.example.testproj.repository.DeviceRepository;
import org.example.testproj.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final DeviceRepository deviceRepository;

    @Transactional
    public GroupResponse createGroup(CreateGroupRequest request) {
        List<UUID> deviceUuids = request.getDeviceIds().stream()
                .map(UUID::fromString)
                .toList();

        List<Device> devices = deviceRepository.findAllByIdIn(deviceUuids);

        Group group = new Group();
        group.setName(request.getName());
        group.setDevices(devices);
        groupRepository.save(group);

        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .deviceIds(devices.stream().map(d -> d.getId().toString()).toList())
                .build();
    }

    public boolean deleteGroup(UUID id) {
        if (!groupRepository.existsById(id)) {
            return false;
        }
        groupRepository.deleteById(id);
        return true;
    }
}
