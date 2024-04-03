package com.ivan.passwordmanager.dto;

import lombok.Data;
import org.mapstruct.Mapper;

@Data
public class NoteDto {

    private Long id;

    private String note;
}
