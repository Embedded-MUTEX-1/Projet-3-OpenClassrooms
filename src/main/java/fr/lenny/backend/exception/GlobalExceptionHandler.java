package fr.lenny.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({LocationNotFoundException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(LocationNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({MessageNotFoundException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(MessageNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleRuntimeException(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("Method not allowed");
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> handleRuntimeException(NoResourceFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No resources found");
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleRuntimeException(AuthenticationException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Authentication failed");
    }

    @ExceptionHandler({UserAlreadyExists.class})
    public ResponseEntity<Object> handleRuntimeException(UserAlreadyExists exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("User already exists");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleRuntimeException(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Bad request");
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleRuntimeException(BadCredentialsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Bad credential");
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        exception.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal error");
    }
}
