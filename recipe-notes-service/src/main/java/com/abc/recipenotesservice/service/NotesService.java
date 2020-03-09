package com.abc.recipenotesservice.service;

import com.abc.recipenotesservice.model.entity.Notes;
import com.abc.recipenotesservice.model.response.NotesResponse;

import java.util.List;

public interface NotesService {
    NotesResponse save(Notes notes);

    NotesResponse findById(Long id);

    NotesResponse findByRecipeName(String recipeId);

    List<NotesResponse> findAll();

    void deleteByRecipeName(String recipeName);

    void deleteAll();

    boolean existByRecipeName(String recipeName);

    Long count();
}
