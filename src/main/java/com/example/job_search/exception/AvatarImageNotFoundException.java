package com.example.job_search.exception;

public class AvatarImageNotFoundException extends NotFoundEntryException {
    public AvatarImageNotFoundException(String s) {
        super("Image not found");
    }
}
