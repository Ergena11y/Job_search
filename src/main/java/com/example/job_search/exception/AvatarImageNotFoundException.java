package com.example.job_search.exception;

public class AvatarImageNotFoundException extends NotFoundEntryException {
    public AvatarImageNotFoundException() {
        super("Image not found");
    }
}
