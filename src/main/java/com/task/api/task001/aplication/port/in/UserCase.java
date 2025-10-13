package com.task.api.task001.aplication.port.in;

import com.task.api.task001.domain.model.UserTask;
import reactor.core.publisher.Mono;

public interface UserCase {
    Mono<UserTask> createUser(UserTask userTask);

}
