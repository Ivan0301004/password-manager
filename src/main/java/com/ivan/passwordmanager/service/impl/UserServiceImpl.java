package com.ivan.passwordmanager.service.impl;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ivan.passwordmanager.dto.UserDto;
import com.ivan.passwordmanager.exeptions.NotFound;
import com.ivan.passwordmanager.mappers.UserMapper;
import com.ivan.passwordmanager.model.User;
import com.ivan.passwordmanager.repository.UserRepository;
import com.ivan.passwordmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> usersList = this.userRepository.findAll(pageable);

        if (usersList.isEmpty())
            throw new NotFound("Empty List", HttpStatus.NO_CONTENT);

        return this.userMapper.toUsersDto(usersList);
    }

    @Override
    public UserDto removeUserById(long id) {
        Optional<User> userToRemove = this.userRepository.findById(id);
        userToRemove.ifPresentOrElse(this.userRepository::delete, () -> {
                    throw new NotFound("User with id " + id + " was not found", HttpStatus.NOT_FOUND);
                }
        );

        return this.userMapper.toUserDto(userToRemove.get());
    }

    @Override
    public User updateUserById(long id, UserDto userDto) {
        return this.userRepository.findById(id)
                .map(user -> {
                    user.setName(userDto.getName());
                    user.setEmail(userDto.getEmail());
                    user.setLastModifiedBy(LocalDateTime.now());
                    return this.userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("Please set some data"));
    }

    @Override
    public User patchUserById(long userId, Map<String, Object> fields) {
        User userToUpdate = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("User doesn't exist", HttpStatus.NOT_FOUND));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, userToUpdate, value);
        });

        userToUpdate.setLastModifiedBy(LocalDateTime.now());
        return this.userRepository.save(userToUpdate);
    }

    @Override
    public User createUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");

        return this.userRepository.save(user);
    }

    @Override
    public UserDto getUserById(long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFound("User with id " + id + " was not found!", HttpStatus.NOT_FOUND));

        return this.userMapper.toUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = this.userRepository.getUserByEmail(email)
                .orElseThrow(() -> new NotFound("User with email " + email + " was not found", HttpStatus.NOT_FOUND));

        return this.userMapper.toUserDto(user);
    }
}
