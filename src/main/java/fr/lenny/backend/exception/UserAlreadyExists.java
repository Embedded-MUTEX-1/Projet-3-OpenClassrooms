package fr.lenny.backend.exception;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists");
    }
}
