package com.abc.recipecircuitbreaker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotesResponse {
    private String recipeName;
    private String recipeNotes;
}
