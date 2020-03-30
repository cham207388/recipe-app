package com.abc.recipecircuitbreaker.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction notesRoute(NotesHandler notesHandler){
        return route(GET("/notes-failer")
        .and(accept(MediaType.APPLICATION_JSON)),
                notesHandler::notesResponseMono);
    }
}
