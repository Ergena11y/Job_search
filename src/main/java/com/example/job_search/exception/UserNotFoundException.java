package com.example.job_search.exception;


public class UserNotFoundException extends NotFoundEntryException {
    public UserNotFoundException() {
        super("User not found");
    }
}
