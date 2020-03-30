package com.abc.recipemainservice.feign;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.response.NotesResponse;
import org.springframework.stereotype.Component;

@Component
public class NotesFallBack implements RecipeFeign{
    @Override
    public NotesResponse save(Notes notes) {
        return null;
    }

    @Override
    public NotesResponse findById(Long id) {
        return null;
    }

    @Override
    public NotesResponse findByRecipeName(String recipeName) {
        return notesResponse();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByRecipeName(String recipeName) {

    }

    @Override
    public String serverInfo() {
        return null;
    }

    private NotesResponse notesResponse(){
        NotesResponse response = new NotesResponse();
        response.setRecipeName("fall back recipe");
        response.setRecipeNotes("fall back Notes");
        return response;
    }
}
