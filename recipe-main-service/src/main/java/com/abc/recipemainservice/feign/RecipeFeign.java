package com.abc.recipemainservice.feign;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.response.NotesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "recipe-notes-service", fallback = NotesFallBack.class)
public interface RecipeFeign {

    @PostMapping(path = "/recipe/notes")
    NotesResponse save(@RequestBody Notes notes);

    @GetMapping(path = "/recipe/notes/id/{id}")
    NotesResponse findById(@PathVariable("id") Long id);

    @GetMapping(path = "/recipe/notes/recipeName/{recipeName}")
    NotesResponse findByRecipeName(@PathVariable("recipeName") String recipeName);

    @DeleteMapping(path = "/recipe/notes/id/{id}")
    void deleteById(@PathVariable("id") Long id);

    @DeleteMapping(path = "/recipe/notes/recipeName/{recipeName}")
    void deleteByRecipeName(@PathVariable("recipeName") String recipeName);

    @GetMapping(path = "/recipe/notes/server-info")
    String serverInfo();
}
