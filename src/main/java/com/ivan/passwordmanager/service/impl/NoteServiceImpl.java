package com.ivan.passwordmanager.service.impl;

import com.ivan.passwordmanager.dto.NoteDto;
import com.ivan.passwordmanager.exeptions.NotFound;
import com.ivan.passwordmanager.mappers.NoteMapper;
import com.ivan.passwordmanager.model.Note;
import com.ivan.passwordmanager.model.Site;
import com.ivan.passwordmanager.repository.NoteRepository;
import com.ivan.passwordmanager.repository.SiteRepository;
import com.ivan.passwordmanager.service.NoteService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final SiteRepository siteRepository;

    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper, SiteRepository siteRepository) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
        this.siteRepository = siteRepository;
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return this.noteMapper.toNotesDto(this.noteRepository.findAll());
    }

    @Override
    @Transactional
    public Note createNoteToSiteById(NoteDto noteDto, Long siteId) {
        return this.siteRepository.findById(siteId)
                .map(site -> {
                    Note notToSave = this.noteMapper.toEntity(noteDto);
                    notToSave.setSite(site);
                    return this.noteRepository.save(notToSave);
                })
                .orElseThrow(() -> new NotFound("No site found", HttpStatus.NOT_FOUND));
    }

    @Override
    public void removeNoteFromSiteById(Long noteId, Long siteId) {
        Note note = this.noteRepository.findById(noteId)
                .orElseThrow(() -> new NotFound("Note was not found", HttpStatus.NOT_FOUND));

        Site site = this.siteRepository.findById(siteId)
                .orElseThrow(() -> new NotFound("Note was not found", HttpStatus.NOT_FOUND));

        if (site.getNotes().contains(note)) {
            site.getNotes().remove(note);
            note.setSite(null);
            this.noteRepository.delete(note);
            this.siteRepository.save(site);
        }
    }

    @Override
    @Transactional
    public Note updateNote(Long id, NoteDto noteDto) {
        return this.noteRepository.findById(id)
                .map(note -> {
                    note.setNote(noteDto.getNote());
                    return this.noteRepository.save(note);
                })
                .orElseThrow(() -> new NotFound("Note was not found", HttpStatus.NOT_FOUND));
    }

}
