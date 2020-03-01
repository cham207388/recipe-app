package com.abc.recipenotesservice.service;

import com.abc.recipenotesservice.domain.Notes;
import com.abc.recipenotesservice.response.NotesResponse;

import java.util.List;

public interface NotesService {
    NotesResponse findByRecipeName(String recipeId);
    List<NotesResponse> findAll();
    NotesResponse findById(Long id);
    NotesResponse save(Notes notes);
    void deleteByRecipeName(String recipeName);
    Long count();
    boolean existByRecipeName(String recipeName);
}
