package com.task.api.task001.aplication.port.out;

import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoRepositoryPort {
    Mono<Alumno> save(Alumno alumno);

    Mono<Boolean> existsById(Long id);

    Flux<Alumno> getAll(Estado alumno);
}
