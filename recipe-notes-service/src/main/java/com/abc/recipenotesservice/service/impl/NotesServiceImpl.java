package com.abc.recipenotesservice.service.impl;

import com.abc.recipenotesservice.model.entity.Notes;
import com.abc.recipenotesservice.model.response.NotesResponse;
import com.abc.recipenotesservice.repository.NotesRepository;
import com.abc.recipenotesservice.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {
    private final NotesRepository notesRepository;
    private final ModelMapper modelMapper;

    @Override
    public NotesResponse save(Notes notes) {
        return modelMapper.map(notesRepository.save(notes), NotesResponse.class);
    }

    @Override
    public NotesResponse findById(Long id) {
        Notes notes = notesRepository.findById(id).orElse(null);
        return getNotesResponse(notes);
    }

    @Override
    public NotesResponse findByRecipeName(String recipeName) {
        Notes notes = notesRepository.findByRecipeName(recipeName).orElse(null);
        return getNotesResponse(notes);
    }

    @Override
    public void deleteById(Long id) {
        notesRepository.deleteById(id);
    }

    @Override
    public void deleteByRecipeName(String recipeName) {
        notesRepository.deleteByRecipeName(recipeName);
    }

    @Override
    public Long count() {
        return notesRepository.count();
    }

    @Override
    public boolean existByRecipeName(String recipeName) {
        return notesRepository.existsByRecipeName(recipeName);
    }

    private NotesResponse getNotesResponse(Notes notes) {
        if (notes != null) {
            return modelMapper.map(notes, NotesResponse.class);
        }
        return null;
    }
}
