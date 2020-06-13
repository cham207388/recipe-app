package com.abc.recipemainservice.feign;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.response.NotesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotesServiceClientFallBack implements NotesServiceClient {

    @Override
    public NotesResponse save(Notes notes) {
        return notesResponse();
    }

    @Override
    public NotesResponse findById(Long id) {
        return notesResponse();
    }

    @Override
    public NotesResponse findByRecipeName(String recipeName) {
        return notesResponse();
    }

    @Override
    public void deleteByRecipeName(String recipeName) {

    }

    private NotesResponse notesResponse(){
        NotesResponse response = new NotesResponse();
        response.setRecipeName("fall back recipe");
        response.setRecipeNotes("fall back Notes");
        return response;
    }
}
