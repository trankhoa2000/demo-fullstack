package com.code.backend.fullstack_backend.exception;

public class NewsNotFoundException extends RuntimeException{
    public NewsNotFoundException(Long id) {
        super("Could not found the news with id " + id);
    }
}
