package fr.lenny.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such message")
public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException() {
        super("No such message");
    }
}
