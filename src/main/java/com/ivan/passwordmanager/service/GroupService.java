package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.GroupDto;
import com.ivan.passwordmanager.model.Group;
import com.ivan.passwordmanager.model.Site;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAllGroups();

    public GroupDto createNewGroupToUser(Group group, Long userId);

    void removeGroupById(Long userId, Long id);

    Group updateGroup(Long id, Long userId, GroupDto groupDto);


}
