package com.example.job_search.exception.hendler;


import com.example.job_search.exception.NotFoundEntryException;
import com.example.job_search.service.ErrorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.sql.SQLException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(NotFoundEntryException.class)
    private String noSuchFileExceptionHandler(HttpServletRequest request, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(SQLException.class)
    private ResponseEntity<ErrorResponseBody> sqlExceptionHandler(SQLException e) {
        return new ResponseEntity<>(errorService.makeResponse(e, e.getClass().getSimpleName()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }
}
