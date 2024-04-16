package com.ivan.passwordmanager.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ivan.passwordmanager.dto.UserDto;
import com.ivan.passwordmanager.model.User;

public interface UserService {

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto removeUserById(long id);

    User updateUserById(long id, UserDto userDto);

    User patchUserById(long userId, Map<String, Object> fields);

    User createUser(User user);

    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

}
