package com.task.api.task001.aplication.service;

import com.task.api.task001.aplication.port.in.UserCase;
import com.task.api.task001.aplication.port.out.UserRepositoryPort;
import com.task.api.task001.domain.exceptions.user.UserAlreadyExistsException;
import com.task.api.task001.domain.model.UserTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserService implements UserCase {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Mono<UserTask> createUser(UserTask userTask) {
        return validateEmail(userTask.email())
                .flatMap(exists -> {
                    if (exists.equals(Boolean.TRUE)) {
                        return Mono.error(
                                new UserAlreadyExistsException("Usuario con email " +
                                userTask.email() + " ya existe"));
                    }
                        return userRepositoryPort.saveUser(userTask)
                                .doOnSuccess(savedUser ->
                                        log.info("Usuario creado con email: {}",
                                                savedUser.email()))
                                .doOnError(error ->
                                        log.error("Error creating user: {}",
                                                error.getMessage()));
                });
    }

    public Mono<Boolean> validateEmail(String email) {
        return userRepositoryPort.getByEmail(email)
                .map(userTask -> true)
                .defaultIfEmpty(false);
    }

}
