package com.ivan.passwordmanager.dto;

import com.ivan.passwordmanager.model.Site;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UserDto {

    private String name;

    private String email;

    private List<Site> siteList;

}
