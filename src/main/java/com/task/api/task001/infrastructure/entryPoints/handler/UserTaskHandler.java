package com.task.api.task001.infrastructure.entryPoints.handler;

import com.task.api.task001.aplication.port.in.UserCase;
import com.task.api.task001.domain.exceptions.user.UserAlreadyExistsException;
import com.task.api.task001.infrastructure.entryPoints.config.validators.UserValidatorRequest;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestUserTask;
import com.task.api.task001.infrastructure.entryPoints.mapper.UserMapper;
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
    private final UserValidatorRequest validator = new UserValidatorRequest();
    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RequestUserTask.class)
                .flatMap(requestValidator-> {
                    // Validar los datos del usuario
                    return validator.validate(
                            requestValidator.name(),
                            requestValidator.email())
                            .thenReturn(requestValidator);
                })
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
                                .onErrorResume(UserAlreadyExistsException.class, e ->
                                        ServerResponse.status(409)
                                                .bodyValue(e.getMessage())
                                ).onErrorResume(Exception.class, e ->
                                        ServerResponse.status(500)
                                                .bodyValue("Unexpected error: " + e.getMessage())

                                )
                );
    }



}
