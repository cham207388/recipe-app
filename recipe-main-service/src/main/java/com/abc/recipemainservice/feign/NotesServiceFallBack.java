package com.abc.recipemainservice.feign;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.response.NotesResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotesServiceFallBack implements NotesServiceFeign {
    private final Throwable throwable;

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
        if(throwable instanceof FeignException && ((FeignException) throwable).status() == 404){
            log.error("404 error by recipe name {}. Error message: {}", recipeName, throwable.getLocalizedMessage());
        }else{
            log.error("A different error. Error message {}", throwable.getLocalizedMessage());
        }
        return notesResponse();
    }

    private NotesResponse notesResponse(){
        NotesResponse response = new NotesResponse();
        response.setRecipeName("fall back recipe");
        response.setRecipeNotes("fall back Notes");
        return response;
    }
}
