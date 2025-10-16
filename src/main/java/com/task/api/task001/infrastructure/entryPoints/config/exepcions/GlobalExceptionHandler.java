package com.task.api.task001.infrastructure.entryPoints.config.exepcions;

import com.task.api.task001.domain.exceptions.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    //  Maneja la excepcion personalizada de usuario existente
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Mono<ResponseEntity<String>> handleUserAlreadyExists(
            UserAlreadyExistsException ex
    ) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage()));
    }

    //  Maneja cualquier otra excepcion no controlada
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handleGenericException(
            Exception ex
    ) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error: " + ex.getMessage()));
    }
}
