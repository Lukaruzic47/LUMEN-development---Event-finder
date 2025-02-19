package com.evento.app.application.exceptions;

public class UserAlreadyExistsException extends IllegalArgumentException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
