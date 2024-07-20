package com.proyect.services.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            return handleValidationException((MethodArgumentNotValidException) ex, headers, status, request);
        }

        // Handle other exceptions if needed
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", status.value());
        errorMap.put("message", "Validation failed. Check 'errors' field for details.");
        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getDefaultMessage());
        }

        String detailMessage = "Invalid request content. " + String.join(". ", errors);
        errorMap.put("detail", detailMessage);

        return new ResponseEntity<>(errorMap, headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMap.put("message", "Internal Server Error");
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}