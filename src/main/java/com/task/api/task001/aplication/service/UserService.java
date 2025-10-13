package com.task.api.task001.aplication.service;

import com.task.api.task001.aplication.port.in.UserCase;
import com.task.api.task001.aplication.port.out.UserRepositoryPort;
import com.task.api.task001.domain.model.UserTask;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService implements UserCase {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Mono<UserTask> createUser(UserTask userTask) {
        return userRepositoryPort.save(userTask);
    }
}
