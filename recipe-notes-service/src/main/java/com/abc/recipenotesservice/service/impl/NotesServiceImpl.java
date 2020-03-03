package com.abc.recipenotesservice.service.impl;

import com.abc.recipenotesservice.domain.Notes;
import com.abc.recipenotesservice.repository.NotesRepository;
import com.abc.recipenotesservice.response.NotesResponse;
import com.abc.recipenotesservice.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
        NotesResponse response = new NotesResponse();
        BeanUtils.copyProperties(notesRepository.findByRecipeName(recipeName), response);
        return response;
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
        NotesResponse response = null;
        Notes notes = notesRepository.findById(id).orElse(null);
        if (notes != null) {
            response = new NotesResponse();
            BeanUtils.copyProperties(notes, response);
        }
        return response;
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
