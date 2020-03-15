package com.abc.recipemainservice.controller.impl;

import com.abc.recipemainservice.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.abc.recipemainservice.util.GenerateStubs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = RecipeControllerImpl.class)
@DisplayName("Recipe Controller Test-> Context")
class RecipeControllerImplTest {

    private static final String LOCALHOST_8080_RECIPE = "http://localhost:8080/recipe";
    private static final String ID_PATH = "/id/1";
    private static final String RECIPE_NAME_PATH = "/recipeName/" + RECIPE_NAME;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        when(recipeService.save(any())).thenReturn(recipeResponse());
        when(recipeService.findById(any())).thenReturn(recipeResponse());
        when(recipeService.findByRecipeName(any())).thenReturn(recipeResponse());
        when(recipeService.findAll()).thenReturn(recipeResponses());
        doNothing().when(recipeService).deleteByRecipeName(any());
        doNothing().when(recipeService).deleteAll();
        doNothing().when(recipeService).delete(any());
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    @DisplayName("save recipe")
    void save() throws Exception {
        mockMvc.perform(post(LOCALHOST_8080_RECIPE)
                .content(objectMapper.writeValueAsString(recipeRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(recipeService).save(any());
    }

    @Test
    @DisplayName("find recipe by id")
    void findById() throws Exception {
        mockMvc.perform(get(LOCALHOST_8080_RECIPE + ID_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredients").exists());
        verify(recipeService).findById(anyLong());
    }

    @Test
    @DisplayName("find recipe by recipe name")
    void findByRecipeName() throws Exception {
        mockMvc.perform(get(LOCALHOST_8080_RECIPE + RECIPE_NAME_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredients").exists());
        verify(recipeService).findByRecipeName(anyString());
    }

    @Test
    @DisplayName("find recipes")
    void findAll() throws Exception {
        mockMvc.perform(get(LOCALHOST_8080_RECIPE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..ingredients").exists());
        verify(recipeService).findAll();
    }

    @Test
    @DisplayName("delete recipe by recipe name")
    void deleteByRecipeName() throws Exception {
        mockMvc.perform(delete(LOCALHOST_8080_RECIPE + RECIPE_NAME_PATH))
                .andExpect(status().isNoContent());
        verify(recipeService).deleteByRecipeName(any());
    }

    @Test
    @DisplayName("delete recipe object")
    void deleteRecipe() throws Exception {
        mockMvc.perform(delete(LOCALHOST_8080_RECIPE)
                .content(objectMapper.writeValueAsString(recipeRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(recipeService).delete(any());
    }

    @Test
    @DisplayName("delete all recipes")
    void deleteAll() throws Exception {
        mockMvc.perform(delete(LOCALHOST_8080_RECIPE + "/all"))
                .andExpect(status().isNoContent());
        verify(recipeService).deleteAll();
    }
}