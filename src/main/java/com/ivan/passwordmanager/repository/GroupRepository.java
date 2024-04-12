package com.ivan.passwordmanager.repository;

import com.ivan.passwordmanager.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByIdAndUserId(Long groupId, Long userId);
}
