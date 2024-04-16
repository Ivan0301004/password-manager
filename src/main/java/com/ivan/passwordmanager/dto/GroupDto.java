package com.ivan.passwordmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivan.passwordmanager.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {

    private Long id;

    private String name;

    @JsonIgnore
    private User user;

}
