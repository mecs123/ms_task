package com.task.api.task001.infrastructure.drivenAdapters.repository;

import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.model.Alumno;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AlumnoRepository
        extends ReactiveCrudRepository<Alumno, Long> {

    Flux<Alumno> findByEstado(Estado estado);
}