package com.task.api.task001.aplication.port.out;

import com.task.api.task001.aplication.dto.ManagerTaskDTO;
import com.task.api.task001.domain.model.ManagerTask;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManagerTaskRepositoryPort {
    Mono<ManagerTask> saveTask(ManagerTaskDTO userTask);

    Flux<ManagerTask> getTasks();

    Mono<ManagerTask> completeTask(Long taskId);

    Mono<Void> deleteTask(Long taskId);
}
