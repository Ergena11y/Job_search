package com.example.job_search.exception;

public class ResumeNotFoundException extends NotFoundEntryException {
    public ResumeNotFoundException(String message) {
        super(message);
    }
}
