package com.task.api.task001.infrastructure.driven_adapters.r2dbc_postgreSql.adapters;

import com.task.api.task001.aplication.port.out.UserRepositoryPort;
import com.task.api.task001.domain.model.UserTask;
import com.task.api.task001.infrastructure.driven_adapters.repository.UserTaskRespository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public class UserTaskRepositoryAdapter implements UserRepositoryPort {

    public UserTaskRepositoryAdapter(UserTaskRespository userTaskRespository) {
        this.userTaskRespository = userTaskRespository;
    }

    private final UserTaskRespository userTaskRespository;
    @Override
    public Mono<UserTask> saveUser(UserTask userTask) {
        return userTaskRespository.saveUserTask(userTask.name(),userTask.email());
    }

    @Override
    public Mono<UserTask> getByEmail(String email) {
        return userTaskRespository.getByEmail(email);
    }
}
