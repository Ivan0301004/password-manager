package com.ivan.passwordmanager.mappers;

import com.ivan.passwordmanager.dto.UserDto;
import com.ivan.passwordmanager.model.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    default Page<UserDto> toUsersDto(Page<User> users) {
        return new PageImpl<>(users.getContent().stream()
                .map(this::toUserDto)
                .collect(Collectors.toList()), users.getPageable(), users.getTotalElements());
    }
}
