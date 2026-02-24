package com.task.api.task001.infrastructure.drivenAdapters.repository;

import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.model.Alumno;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoRepository
        extends ReactiveCrudRepository<Alumno, Long> {

    @Query("""
                INSERT INTO alumno (id, nombre, apellido, estado, edad)
                VALUES (:id, :nombre, :apellido, :estado, :edad)
            """)
    Mono<Void> insertAlumno(
            Long id,
            String nombre,
            String apellido,
            Estado estado,
            Integer edad
    );

    Flux<Alumno> findByEstado(Estado estado);
}