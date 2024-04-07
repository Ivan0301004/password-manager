package com.ivan.passwordmanager.mappers;

import com.ivan.passwordmanager.dto.NoteDto;
import com.ivan.passwordmanager.model.Note;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDto toDto(Note note);

    Note toEntity(NoteDto noteDto);

    List<NoteDto> toNotesDto(List<Note> notes);
}
