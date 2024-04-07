package com.ivan.passwordmanager.mappers;

import com.ivan.passwordmanager.dto.GroupDto;
import com.ivan.passwordmanager.model.Group;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupDto groupDto);

    GroupDto toDto(Group group);

    List<GroupDto> toDtoList(List<Group> groups);
}
