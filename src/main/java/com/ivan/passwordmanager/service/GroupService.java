package com.ivan.passwordmanager.service;

import java.util.List;

import com.ivan.passwordmanager.dto.GroupDto;
import com.ivan.passwordmanager.model.Group;

public interface GroupService {

    List<GroupDto> getAllGroups();

    public GroupDto createNewGroupToUser(Group group, Long userId);

    void removeGroupById(Long userId, Long id);

    Group updateGroup(Long id, Long userId, GroupDto groupDto);


}
