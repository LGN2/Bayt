// Keep billing controller error handling in this package.
package com.property.billing.controller;

// Import HTTP status values for error responses.
import org.springframework.http.HttpStatus;
// Import ResponseEntity to build HTTP responses.
import org.springframework.http.ResponseEntity;
// Import validation exception type for invalid request data.
import org.springframework.web.bind.MethodArgumentNotValidException;
// Import annotation used to choose handled exception types.
import org.springframework.web.bind.annotation.ExceptionHandler;
// Import annotation for shared REST controller error handling.
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Import HashMap for building error response bodies.
import java.util.HashMap;
// Import Map for returning key-value error messages.
import java.util.Map;

// Handle errors for all REST controllers.
@RestControllerAdvice
// Provide common billing API exception handling.
public class BillingExceptionHandler {

    // Catch invalid argument errors.
    @ExceptionHandler(IllegalArgumentException.class)
    // Return a simple bad request response for invalid arguments.
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        // Create a map for the error response.
        Map<String, String> error = new HashMap<>();
        // Add the exception message to the response.
        error.put("error", ex.getMessage());
        // Return HTTP 400 with the error details.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Catch validation errors from request data.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Return a simple bad request response for validation failures.
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        // Create a map for the validation error response.
        Map<String, String> error = new HashMap<>();
        // Add a general validation message to the response.
        error.put("error", "Invalid request data");
        // Return HTTP 400 with the validation error details.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
