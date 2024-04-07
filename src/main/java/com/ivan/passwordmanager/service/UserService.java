package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.UserDto;
import com.ivan.passwordmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto removeUserById(long id);

    User updateUserById(long id, UserDto userDto);

    User createUser(User user);

    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);



}
