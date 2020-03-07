package com.abc.recipemainservice.controller;

import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.request.RecipeRequest;
import com.abc.recipemainservice.model.response.RecipeResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface RecipeController {

    @ApiOperation(value = "save recipe")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Succuss response", response = RecipeResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error")
    })
    RecipeResponse save(RecipeRequest recipe);

    @ApiOperation(value = "find recipe by recipe id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = RecipeResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error")
    })
    RecipeResponse findById(Long id);

    @ApiOperation(value = "find recipe by recipe name")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = RecipeResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error")
    })
    RecipeResponse findByRecipeName(String recipeName);

    @ApiOperation(value = "find all notes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response", response = List.class),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error")
    })
    List<RecipeResponse> findAll();

    @ApiOperation(value = "delete recipe by recipe name")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response"),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error")
    })
    void deleteByRecipeName(String recipeName);

    @ApiOperation(value = "delete recipe")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response"),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error")
    })
    void delete(Recipe recipe);

    @ApiOperation(value = "delete all recipes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Succuss response"),
            @ApiResponse(code = HttpServletResponse.SC_SERVICE_UNAVAILABLE, message = "Internal Server error")
    })
    void deleteAll();
}
