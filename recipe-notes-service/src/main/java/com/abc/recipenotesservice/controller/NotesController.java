package com.abc.recipenotesservice.controller;

import com.abc.recipenotesservice.domain.Notes;
import com.abc.recipenotesservice.response.NotesResponse;
import com.abc.recipenotesservice.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;

    @GetMapping(path = "/recipeName/{recipeName}")
    public NotesResponse findByRecipeId(@PathVariable("recipeName") String recipeName) {
        return notesService.findByRecipeName(recipeName);
    }

    @GetMapping
    public List<NotesResponse> findAll() {
        return notesService.findAll();
    }

    @GetMapping(path = "/id/{id}")
    public NotesResponse findById(@PathVariable("id") Long id){
        return notesService.findById(id);
    }

    @PostMapping
    public NotesResponse save(@RequestBody Notes notes) {
        return notesService.save(notes);
    }
}
