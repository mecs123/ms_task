package com.task.api.task001.domain.exceptions.user;

public class AlumnoAlreadyExistsException extends RuntimeException {
    public AlumnoAlreadyExistsException(String message) {
        super(message);
    }
}
