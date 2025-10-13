package com.task.api.task001.aplication.service;

import com.task.api.task001.aplication.dto.ManagerTaskDTO;
import com.task.api.task001.aplication.port.in.ManagerTaskCase;
import com.task.api.task001.aplication.port.out.ManagerTaskRepositoryPort;
import com.task.api.task001.domain.model.ManagerTask;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ManagerTaskService implements ManagerTaskCase {

    private final ManagerTaskRepositoryPort managerTaskRepositoryPort;

    public ManagerTaskService(ManagerTaskRepositoryPort managerTaskRepositoryPort) {
        this.managerTaskRepositoryPort = managerTaskRepositoryPort;
    }

    @Override
    public Mono<ManagerTask> createTask(ManagerTaskDTO userTask) {
        return managerTaskRepositoryPort.saveTask(userTask);
    }
}
