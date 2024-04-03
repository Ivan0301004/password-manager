package com.ivan.passwordmanager.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class SiteDto {

    private Long id;

    private String name;

    private String email;

    private String password;
}
