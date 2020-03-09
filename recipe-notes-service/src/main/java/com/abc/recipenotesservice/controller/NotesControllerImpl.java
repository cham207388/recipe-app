package com.abc.recipenotesservice.controller;

import com.abc.recipenotesservice.model.entity.Notes;
import com.abc.recipenotesservice.model.response.NotesResponse;
import com.abc.recipenotesservice.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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
    public NotesResponse save(@RequestBody Notes notes) {
        return notesService.save(notes);
    }

    @Override
    @GetMapping(path = "/id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public NotesResponse findById(@PathVariable("id") Long id) {
        return notesService.findById(id);
    }

    @Override
    @GetMapping(path = "/recipeName/{recipeName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public NotesResponse findByRecipeName(@PathVariable("recipeName") String recipeName) {
        return notesService.findByRecipeName(recipeName);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NotesResponse> findAll() {
        return notesService.findAll();
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
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        notesService.deleteAll();
    }

    @GetMapping(path = "/server-info")
    public String serverInfo() {
        return "Port number is: " + environment.getProperty("local.server.port");
    }
}
