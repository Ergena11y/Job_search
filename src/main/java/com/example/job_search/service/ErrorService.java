package com.example.job_search.service;

import com.example.job_search.exception.hendler.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception e, String exceptionClass);

    ErrorResponseBody makeResponse(BindingResult bindingResult);
}
