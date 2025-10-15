package com.task.api.task001.aplication.port.out;

import com.task.api.task001.domain.model.UserTask;
import reactor.core.publisher.Mono;

public interface UserRepositoryPort {
    Mono<UserTask> saveUser(UserTask userTask);
    Mono<UserTask> getByEmail(String email);
}
