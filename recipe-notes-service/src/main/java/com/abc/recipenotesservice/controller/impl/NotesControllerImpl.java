package com.abc.recipenotesservice.controller.impl;

import com.abc.recipenotesservice.controller.NotesController;
import com.abc.recipenotesservice.model.entity.Notes;
import com.abc.recipenotesservice.model.response.NotesResponse;
import com.abc.recipenotesservice.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path = "/recipe/notes")
@RequiredArgsConstructor
@Transactional
public class NotesControllerImpl implements NotesController {
    private final NotesService notesService;
    private final Environment environment;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public NotesResponse save(@RequestBody Notes notes) {
        return notesService.save(notes);
    }

    @Override
    @GetMapping(path = "/id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public NotesResponse findById(@PathVariable("id") Long id) {
        return notesService.findById(id);
    }

    @Override
    @GetMapping(path = "/recipeName/{recipeName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public NotesResponse findByRecipeName(@PathVariable("recipeName") String recipeName) {
        return notesService.findByRecipeName(recipeName);
    }

    @Override
    @DeleteMapping(path = "/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        notesService.deleteById(id);
    }

    @Override
    @DeleteMapping(path = "/recipeName/{recipeName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByRecipeName(@PathVariable("recipeName") String recipeName) {
        if (notesService.existByRecipeName(recipeName)) {
            notesService.deleteByRecipeName(recipeName);
        }
    }

    @Override
    @GetMapping(path = "/server-info")
    @ResponseStatus(HttpStatus.OK)
    public String serverInfo() {
        return "Active port in notes service: " + environment.getProperty("local.server.port");
    }
}
