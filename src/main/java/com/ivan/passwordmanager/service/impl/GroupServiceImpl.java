package com.ivan.passwordmanager.service.impl;

import com.ivan.passwordmanager.dto.GroupDto;
import com.ivan.passwordmanager.exeptions.NotFound;
import com.ivan.passwordmanager.mappers.GroupMapper;
import com.ivan.passwordmanager.model.Category;
import com.ivan.passwordmanager.model.Group;
import com.ivan.passwordmanager.model.User;
import com.ivan.passwordmanager.repository.GroupRepository;
import com.ivan.passwordmanager.repository.UserRepository;
import com.ivan.passwordmanager.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<GroupDto> getAllGroups() {
        return this.groupMapper.toDtoList(this.groupRepository.findAll());
    }

    @Override
    public Group createNewGroupToUser(Group group, Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("User not found", HttpStatus.NOT_FOUND));

        if (!user.getGroupList().contains(group.getName())) {
            group.setUser(user);
            user.getGroupList().add(group);
            this.groupRepository.save(group);
            this.userRepository.save(user);
        }

        return group;
    }

    @Override
    public void removeGroupById(Long groupId, Long userId) {
        Group groupToRemove = this.groupRepository.findById(groupId).orElseThrow();

        this.userRepository.findById(userId)
                .ifPresent(user -> {
                    user.getGroupList()
                            .removeIf(group -> group.getId().equals(groupId));
                    this.userRepository.save(user);
                });
    }

    @Override
    public Group updateGroup(Long groupId, Long userId, GroupDto groupDto) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("Not found", HttpStatus.NOT_FOUND));

        return this.groupRepository.findById(groupId)
                .map(group -> {
                    group.setName(groupDto.getName());
                    return this.groupRepository.save(group);
                })
                .orElseThrow(() -> new NotFound("Not found", HttpStatus.NOT_FOUND));
    }
}
