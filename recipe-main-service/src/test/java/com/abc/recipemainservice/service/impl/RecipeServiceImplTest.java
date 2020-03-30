package com.abc.recipemainservice.service.impl;

import com.abc.recipemainservice.feign.RecipeFeign;
import com.abc.recipemainservice.model.response.RecipeResponse;
import com.abc.recipemainservice.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.abc.recipemainservice.util.GenerateStubs.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RecipeServiceImplTest {

    @InjectMocks
    private RecipeServiceImpl classUnderTest;

    @Mock
    private RecipeRepository repository;

    @Mock
    private RecipeFeign recipeFeign;

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    void setUp() {
        when(modelMapper.map(eq(recipeRequest()), any())).thenReturn(recipe());
        when(modelMapper.map(eq(recipe()), any())).thenReturn(recipeResponse());
        when(repository.save(any())).thenReturn(recipe());
        when(repository.findAll()).thenReturn(recipes());
        when(repository.findById(anyLong())).thenReturn(Optional.of(recipe()));
        when(repository.findByRecipeName(anyString())).thenReturn(Optional.of(recipe()));
        when(repository.existsByRecipeName(any())).thenReturn(true);
        when(recipeFeign.save(any())).thenReturn(notesResponse());
        when(recipeFeign.findById(anyLong())).thenReturn(notesResponse());
        when(recipeFeign.findByRecipeName(anyString())).thenReturn(notesResponse());
    }

    @Test
    @DisplayName("save a recipe")
    void save() {
        RecipeResponse recipeResponse = classUnderTest.save(recipeRequest());

        verify(modelMapper, times(2)).map(any(), any());
        verify(repository, atMost(1)).save(any());
        verify(recipeFeign, atMost(1)).save(any());

        assertNotNull(recipeResponse);
    }

    @Test
    @DisplayName("find recipe by id")
    void findById() {
        RecipeResponse recipeResponse = classUnderTest.findById(1L);

        verify(modelMapper, times(1)).map(any(), any());
        verify(repository, times(1)).findById(anyLong());
        verify(recipeFeign, atMost(1)).findById(any());

        assertNotNull(recipeResponse);
    }

    @Test
    @DisplayName("find recipe by name")
    void findByRecipeName() {
        RecipeResponse recipeResponse = classUnderTest.findByRecipeName(RECIPE_NAME);

        verify(repository, atMost(1)).findByRecipeName(anyString());
        verify(modelMapper, atMost(1)).map(any(), any());
        verify(recipeFeign, atMost(1)).findByRecipeName(any());

        assertNotNull(recipeResponse);
    }

    @Test
    @DisplayName("find all recipe")
    void findAll() {
        List<RecipeResponse> recipeResponses = classUnderTest.findAll();

        verify(repository, atMost(1)).findByRecipeName(anyString());
        verify(modelMapper, atLeast(1)).map(any(), any());
        verify(recipeFeign, atMost(1)).findByRecipeName(any());

        assertAll("checking the responses", () -> {
            assertEquals(1, recipeResponses.size());
            assertEquals(RECIPE_NAME, recipeResponses.get(0).getRecipeName());
            assertEquals(recipeResponse(), recipeResponses.get(0));
        });
    }

    @Test
    @DisplayName("delete recipe by name")
    void deleteByRecipeName() {
        classUnderTest.deleteByRecipeName(RECIPE_NAME);
        verify(repository, times(1)).existsByRecipeName(anyString());
    }

    @Test
    @DisplayName("check the count")
    void count() {
        when(repository.count()).thenReturn(3L);
        long count = classUnderTest.count();
        assertEquals(3L, count);
    }
}