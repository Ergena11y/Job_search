package com.example.job_search.exception;

public class UserProfileNotFoundException extends NotFoundEntryException {
    public UserProfileNotFoundException() {
        super("User profile not found");
    }

    public  UserProfileNotFoundException(String message){
        super(message);
    }
}
