package com.example.job_search.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("error.403.description");
    }
}
