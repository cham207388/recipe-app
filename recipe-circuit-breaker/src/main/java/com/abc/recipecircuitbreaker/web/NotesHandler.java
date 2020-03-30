package com.abc.recipecircuitbreaker.web;

import com.abc.recipecircuitbreaker.model.NotesResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class NotesHandler {
    public Mono<ServerResponse> notesResponseMono(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(
                        NotesResponse.builder()
                                .recipeName("fall back recipe")
                                .recipeNotes("Notes service is unavailable")
                                .build()), NotesResponse.class);
    }
}
