package com.epam.homework8.exception;

public class EmptyArrayRuntimeException extends RuntimeException {
    public EmptyArrayRuntimeException() {
        super();
    }

    public EmptyArrayRuntimeException(String message) {
        super(message);
    }
}
