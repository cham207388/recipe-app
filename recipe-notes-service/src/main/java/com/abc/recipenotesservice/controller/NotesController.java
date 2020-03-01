package com.abc.recipenotesservice.controller;

import com.abc.recipenotesservice.domain.Notes;
import com.abc.recipenotesservice.response.NotesResponse;
import com.abc.recipenotesservice.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public NotesResponse save(@RequestBody Notes notes) {
        return notesService.save(notes);
    }

    @GetMapping(path = "/recipeName/{recipeName}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public NotesResponse findByRecipeName(@PathVariable("recipeName") String recipeName) {
        return notesService.findByRecipeName(recipeName);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<NotesResponse> findAll() {
        return notesService.findAll();
    }

    @GetMapping(path = "/id/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public NotesResponse findById(@PathVariable("id") Long id) {
        return notesService.findById(id);
    }

    @DeleteMapping(path = "/recipeName/{recipeName}")
    public ResponseEntity<Void> deleteByRecipeName(@PathVariable("recipeName") String recipeName) {
        if (notesService.existByRecipeName(recipeName)) {
            notesService.deleteByRecipeName(recipeName);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
