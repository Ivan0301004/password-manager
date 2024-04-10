package com.ivan.passwordmanager.dto;

import com.ivan.passwordmanager.model.Site;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedBy;

    private List<Site> siteList;
}
