package com.task.api.task001.infrastructure.entry_points.handler;

import com.task.api.task001.aplication.port.in.UserCase;
import com.task.api.task001.infrastructure.entry_points.dto.Request.RequestUserTask;
import com.task.api.task001.infrastructure.entry_points.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserTaskHandler {

    private final UserCase userCase;
    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RequestUserTask.class)
                .map(UserMapper.INSTANCE::toDomain)
                .flatMap(userTask ->
                        // devuelve Mono<UserTask>
                        userCase.createUser(userTask)
                                // mapeamos el UserTask a ResponseUserTask
                                .map(UserMapper.INSTANCE::toResponse)
                                .flatMap(responseUserTask ->
                                        ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(responseUserTask)
                                )
                );
    }



}
