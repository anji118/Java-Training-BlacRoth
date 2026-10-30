package com.training.library.exceptions;

public class DuplicateBookException extends RuntimeException {

    public DuplicateBookException(String message) {
        super(message);
    }
}