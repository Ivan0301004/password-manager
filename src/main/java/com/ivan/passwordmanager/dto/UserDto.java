package com.ivan.passwordmanager.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.ivan.passwordmanager.model.Group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable {

    private Long id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedBy;

    private List<Group> groupList;
}
