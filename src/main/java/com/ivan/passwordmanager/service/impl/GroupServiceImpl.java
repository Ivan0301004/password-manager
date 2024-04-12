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
import jakarta.persistence.Temporal;
import jakarta.transaction.Transactional;
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
    public GroupDto createNewGroupToUser(Group group, Long userId) {
        return this.userRepository.findById(userId)
                .map(user -> {
                    group.setUser(user);
                    user.getGroupList().add(group);
                    return this.groupMapper.toDto(this.groupRepository.save(group));
                })
                .orElseThrow(() -> new NotFound("User not found", HttpStatus.NOT_FOUND));
    }


    @Transactional
    @Override
    public void removeGroupById(Long userId, Long groupId) {
        Group groupToRemove = this.groupRepository.findById(groupId).orElseThrow();
        this.userRepository.findById(userId)
                .map(user -> {
                    user.getGroupList().remove(groupToRemove);
                    groupToRemove.setUser(null);
                    this.groupRepository.delete(groupToRemove);
                    return this.groupMapper.toDto(groupToRemove);
                })
                .orElseThrow(() -> new NotFound("Error", HttpStatus.NOT_ACCEPTABLE));
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
