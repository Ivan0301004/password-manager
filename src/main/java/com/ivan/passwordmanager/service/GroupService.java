package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.GroupDto;
import com.ivan.passwordmanager.model.Group;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAllGroups();

    Group createNewGroup(Group group);

    void removeGroupById(Long id);

    Group updateGroup(Long id, GroupDto groupDto);

}
