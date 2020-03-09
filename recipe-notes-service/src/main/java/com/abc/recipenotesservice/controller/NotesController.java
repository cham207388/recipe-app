package com.abc.recipenotesservice.controller;

import com.abc.recipenotesservice.exception.NotesException;
import com.abc.recipenotesservice.model.entity.Notes;
import com.abc.recipenotesservice.model.response.NotesResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface NotesController {

    @ApiOperation(value = "save recipe notes and return notes response")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Succuss response", response = NotesResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    NotesResponse save(@RequestBody Notes notes);

    @ApiOperation(value = "find notes by recipe name")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = NotesResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    NotesResponse findByRecipeName(String recipeName);

    @ApiOperation(value = "find all notes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = List.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    List<NotesResponse> findAll();

    @ApiOperation(value = "find recipe notes by notes id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = NotesResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    NotesResponse findById(Long id);

    @ApiOperation(value = "delete notes by recipe name")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = NotesResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    void deleteByRecipeName(String recipeName);

    @ApiOperation(value = "delete all recipe notes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = NotesResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    void deleteAll();
}
