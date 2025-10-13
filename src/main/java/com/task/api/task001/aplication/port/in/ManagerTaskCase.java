package com.task.api.task001.aplication.port.in;

import com.task.api.task001.aplication.dto.ManagerTaskDTO;
import com.task.api.task001.domain.model.ManagerTask;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ManagerTaskCase {
    Mono<ManagerTask> createTask(ManagerTaskDTO userTask);

}
