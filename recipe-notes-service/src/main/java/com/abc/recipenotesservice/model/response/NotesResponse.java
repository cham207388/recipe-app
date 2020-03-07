package com.abc.recipenotesservice.model.response;

import lombok.Data;

@Data
public class NotesResponse {
    private String recipeName;
    private String recipeNotes;
}
