package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.GroupDto;
import com.ivan.passwordmanager.model.Group;
import com.ivan.passwordmanager.model.Site;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAllGroups();

    Group createNewGroupToUser(Group group, Long userId);

    void removeGroupById(Long id, Long userId);

    Group updateGroup(Long id, Long userId, GroupDto groupDto);

}
