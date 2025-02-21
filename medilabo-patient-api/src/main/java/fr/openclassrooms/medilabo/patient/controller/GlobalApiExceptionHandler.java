package fr.openclassrooms.medilabo.patient.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalApiExceptionHandler
{
    // Handle validation errors for @Valid requests
    @ExceptionHandler( ConstraintViolationException.class )
    public ResponseEntity<Map<String, String>> handleConstraintViolation( ConstraintViolationException ex )
    {
        // Extract validation errors and create a custom error response
        Map<String, String> errors = new HashMap<>( );
        ex.getConstraintViolations( ).forEach(constraintViolation -> {
            String field = constraintViolation.getPropertyPath( ).toString( );
            String message = constraintViolation.getMessage( );
            errors.put( field, message );
        });

        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( errors );
    }
}
