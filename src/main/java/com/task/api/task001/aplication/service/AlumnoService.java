package com.task.api.task001.aplication.service;

import com.task.api.task001.aplication.port.in.AlumnoCase;
import com.task.api.task001.aplication.port.out.AlumnoRepositoryPort;
import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.exceptions.user.AlumnoAlreadyExistsException;
import com.task.api.task001.domain.model.Alumno;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AlumnoService implements AlumnoCase {

    private final AlumnoRepositoryPort alumnoRepositoryPort;

    public AlumnoService(AlumnoRepositoryPort alumnoRepositoryPort) {
        this.alumnoRepositoryPort = alumnoRepositoryPort;
    }

    @Override
    public Mono<Void> crearAlumno(Alumno alumno) {
        return alumnoRepositoryPort.existsById(alumno.getId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new AlumnoAlreadyExistsException(
                                        "Alumno con id: " + alumno.getId() + " ya existe"
                                )
                        );
                    }
                    return alumnoRepositoryPort.save(alumno)
                            .doOnSuccess(saved ->
                                    log.info("Alumno creado correctamente: {}", saved.getId()))
                            .doOnError(error ->
                                    log.error("Error creando el alumno: {}", error.getMessage()))
                            .then();
                });
    }

    @Override
    public Flux<Alumno> obtenerActivos() {
        return alumnoRepositoryPort.getAll(Estado.ACTIVO)
                .doOnNext(a -> log.info("Alumno activo encontrado: {}", a.getId()));
    }


}