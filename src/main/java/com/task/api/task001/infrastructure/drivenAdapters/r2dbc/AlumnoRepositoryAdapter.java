package com.task.api.task001.infrastructure.drivenAdapters.r2dbc;

import com.task.api.task001.aplication.port.out.AlumnoRepositoryPort;
import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.model.Alumno;
import com.task.api.task001.infrastructure.drivenAdapters.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AlumnoRepositoryAdapter implements AlumnoRepositoryPort {

    private final AlumnoRepository repository;

    @Override
    public Mono<Alumno> save(Alumno alumno) {
        return repository.insertAlumno(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getApellido(),
                alumno.getEstado(),
                alumno.getEdad()
        ).thenReturn(alumno);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Flux<Alumno> getAll(Estado estadoByAlumno) {
        return repository.findByEstado(estadoByAlumno);
    }
}