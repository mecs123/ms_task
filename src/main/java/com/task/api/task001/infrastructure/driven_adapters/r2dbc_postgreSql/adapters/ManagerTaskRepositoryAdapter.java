package com.task.api.task001.infrastructure.driven_adapters.r2dbc_postgreSql.adapters;

import com.task.api.task001.aplication.dto.ManagerTaskDTO;
import com.task.api.task001.aplication.port.out.ManagerTaskRepositoryPort;
import com.task.api.task001.domain.model.ManagerTask;
import com.task.api.task001.infrastructure.driven_adapters.repository.ManagerTaskRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ManagerTaskRepositoryAdapter implements ManagerTaskRepositoryPort {

    private final ManagerTaskRepository managerTaskRepository;

    public ManagerTaskRepositoryAdapter(ManagerTaskRepository managerTaskRepository) {
        this.managerTaskRepository = managerTaskRepository;
    }

    @Override
    public Mono<ManagerTask> saveTask(ManagerTaskDTO userTask) {
        return managerTaskRepository.createTask(
                userTask.title(),
                userTask.description(),
                userTask.status(),
                userTask.dateend()
        );
    }

    @Override
    public Flux<ManagerTask> getTasks() {
        return null;
    }

    @Override
    public Mono<ManagerTask> completeTask(Long taskId) {
        return null;
    }

    @Override
    public Mono<Void> deleteTask(Long taskId) {
        return null;
    }
}
