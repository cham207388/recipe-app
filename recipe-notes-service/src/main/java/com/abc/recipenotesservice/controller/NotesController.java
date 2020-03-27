package com.abc.recipenotesservice.controller;

import com.abc.recipenotesservice.exception.NotesException;
import com.abc.recipenotesservice.model.entity.Notes;
import com.abc.recipenotesservice.model.response.NotesResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;

public interface NotesController {

    @ApiOperation(value = "save recipe notes and return notes response")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Succuss response", response = NotesResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    NotesResponse save(@RequestBody Notes notes);

    @ApiOperation(value = "find recipe notes by notes id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = NotesResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    NotesResponse findById(Long id);

    @ApiOperation(value = "find notes by recipe name")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = NotesResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    NotesResponse findByRecipeName(String recipeName);

    @ApiOperation(value = "delete notes by id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Succuss response"),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    void deleteById(Long id);

    @ApiOperation(value = "delete notes by recipe name")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Succuss response"),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error", response = NotesException.class)
    })
    void deleteByRecipeName(String recipeName);

    String serverInfo();
}
