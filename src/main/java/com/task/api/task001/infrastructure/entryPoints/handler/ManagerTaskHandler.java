package com.task.api.task001.infrastructure.entryPoints.handler;

import com.task.api.task001.aplication.port.in.ManagerTaskCase;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestManagerTask;
import com.task.api.task001.infrastructure.entryPoints.mapper.ManagerTaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ManagerTaskHandler {

    private final ManagerTaskCase managerTaskCase;

    public Mono<ServerResponse> createTask(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RequestManagerTask.class)
                .map(ManagerTaskMapper.INSTANCE::toDomain)
                .flatMap(managerTask ->
                        // devuelve Mono<UserTask>
                        managerTaskCase.createTask(managerTask)
                                // mapeamos el UserTask a ResponseUserTask
                                .map(ManagerTaskMapper.INSTANCE::toResponse)
                                .flatMap(responseManagerTask ->
                                        ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(responseManagerTask)
                                )
                );
    }


}
