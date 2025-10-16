package com.task.api.task001.infrastructure.entryPoints.config.exepcions;


public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }


}
