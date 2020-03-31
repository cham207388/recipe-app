package com.abc.recipemainservice.feign;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.response.NotesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "recipe-notes-service", fallback = NotesFallbackFactory.class)
public interface NotesServiceFeign {

    @PostMapping(path = "/recipe/notes")
    NotesResponse save(@RequestBody Notes notes);

    @GetMapping(path = "/recipe/notes/id/{id}")
    NotesResponse findById(@PathVariable("id") Long id);

    @GetMapping(path = "/recipe/notes/recipeName/{recipeName}")
    NotesResponse findByRecipeName(@PathVariable("recipeName") String recipeName);
}
