package com.abc.recipenotesservice.service;

import com.abc.recipenotesservice.model.entity.Notes;
import com.abc.recipenotesservice.model.response.NotesResponse;

public interface NotesService {
    NotesResponse save(Notes notes);

    NotesResponse findById(Long id);

    NotesResponse findByRecipeName(String recipeId);

    void deleteById(Long id);

    void deleteByRecipeName(String recipeName);

    boolean existByRecipeName(String recipeName);

    Long count();
}
