package com.task.api.task001.infrastructure.entry_points.config.exepcions;


public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }


}
