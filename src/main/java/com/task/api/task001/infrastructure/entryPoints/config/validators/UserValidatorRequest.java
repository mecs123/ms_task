package com.task.api.task001.infrastructure.entryPoints.config.validators;

import com.task.api.task001.infrastructure.entryPoints.config.exepcions.ValidationException;
import reactor.core.publisher.Mono;

public class UserValidatorRequest {

    public UserValidatorRequest() {
    }

    public Mono<Void> validate(String name, String email) {
        return Mono.defer(() -> {
            if (name == null || name.isBlank()) {
                return Mono.error(new ValidationException("Name cannot be empty"));
            }
            if (!isValidEmail(email)) {
                return Mono.error(new ValidationException("Invalid email format"));
            }
            return Mono.empty();
        });
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
