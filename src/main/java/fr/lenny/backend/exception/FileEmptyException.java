package fr.lenny.backend.exception;

public class FileEmptyException extends RuntimeException {
    public FileEmptyException() {
        super("File is empty");
    }
}
