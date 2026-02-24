package com.task.api.task001.infrastructure.entryPoints.handler;

import com.task.api.task001.aplication.port.in.AlumnoCase;
import com.task.api.task001.domain.exceptions.user.AlumnoAlreadyExistsException;
import com.task.api.task001.domain.model.Alumno;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestAlumnoDTO;
import com.task.api.task001.infrastructure.entryPoints.mapper.AlumnoMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class AlumnoHandler {

    public AlumnoHandler(AlumnoCase alumnoCase, AlumnoMapper alumnoMapper) {
        this.alumnoCase = alumnoCase;
        this.alumnoMapper = alumnoMapper;
    }

    private final AlumnoCase alumnoCase;
    private final AlumnoMapper alumnoMapper;

    public Mono<ServerResponse> crearAlumno(ServerRequest request) {
        return request.bodyToMono(RequestAlumnoDTO.class)
                .map(alumnoMapper::toDomain)
                .doOnNext(a -> System.out.println("ID recibido: " + a.getId()))
                .flatMap(alumno ->
                        alumnoCase.crearAlumno(alumno)
                                .then(ServerResponse.status(201).build())
                )
                .onErrorResume(AlumnoAlreadyExistsException.class, e ->
                        ServerResponse.status(409)
                                .bodyValue(e.getMessage())
                )
                .onErrorResume(Exception.class, e -> {
                    e.printStackTrace();
                    return ServerResponse.status(500)
                            .bodyValue("Unexpected error: " + e.getMessage());
                });
    }


    public Mono<ServerResponse> obtenerActivos(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(alumnoCase.obtenerActivos(), Alumno.class);
    }
}