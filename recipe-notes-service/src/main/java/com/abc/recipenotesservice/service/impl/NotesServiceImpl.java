package com.abc.recipenotesservice.service.impl;

import com.abc.recipenotesservice.domain.Notes;
import com.abc.recipenotesservice.repository.NotesRepository;
import com.abc.recipenotesservice.response.NotesResponse;
import com.abc.recipenotesservice.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {
    private final NotesRepository notesRepository;
    private final ModelMapper modelMapper;

    @Override
    public NotesResponse findByRecipeName(String recipeName) {
        return modelMapper.map(notesRepository.findByRecipeName(recipeName), NotesResponse.class);
    }

    @Override
    public List<NotesResponse> findAll() {
        List<NotesResponse> responses = new ArrayList<>();
        notesRepository.findAll()
                .forEach(notes -> responses.add(modelMapper.map(notes, NotesResponse.class)));
        return responses;
    }

    @Override
    public NotesResponse findById(Long id) {
        Notes notes = notesRepository.findById(id).orElse(null);
        if (notes != null) {
           return modelMapper.map(notes, NotesResponse.class);
        }
        return null;
    }

    @Override
    public NotesResponse save(Notes notes) {
        Notes save = notesRepository.save(notes);
        return modelMapper.map(save, NotesResponse.class);
    }

    @Override
    public void deleteByRecipeName(String recipeName) {
        notesRepository.deleteByRecipeName(recipeName);
    }

    @Override
    public void deleteAll() {
        notesRepository.deleteAll();
    }

    @Override
    public Long count() {
        return notesRepository.count();
    }

    @Override
    public boolean existByRecipeName(String recipeName) {
        return notesRepository.existsByRecipeName(recipeName);
    }
}
