package com.task.api.task001.aplication.port.in;

import com.task.api.task001.domain.model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoCase {
    Mono<Void> crearAlumno(Alumno alumno);

    Flux<Alumno> obtenerActivos();
}
