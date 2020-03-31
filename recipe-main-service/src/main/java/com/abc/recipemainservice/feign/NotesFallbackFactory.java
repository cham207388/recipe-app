package com.abc.recipemainservice.feign;

import feign.hystrix.FallbackFactory;

public class NotesFallbackFactory implements FallbackFactory<NotesServiceFeign> {

    @Override
    public NotesServiceFeign create(Throwable throwable) {
        return new NotesServiceFallBack(throwable);
    }
}
