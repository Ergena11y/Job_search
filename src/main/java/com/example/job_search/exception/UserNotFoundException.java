package com.example.job_search.exception;

import java.nio.file.NoSuchFileException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
