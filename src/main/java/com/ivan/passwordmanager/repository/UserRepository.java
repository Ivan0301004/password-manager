package com.ivan.passwordmanager.repository;

import com.ivan.passwordmanager.dto.UserDto;
import com.ivan.passwordmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByEmail(String email);

}
