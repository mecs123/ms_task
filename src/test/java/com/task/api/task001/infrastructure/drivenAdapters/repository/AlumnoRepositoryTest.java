package com.task.api.task001.infrastructure.drivenAdapters.repository;

import com.task.api.task001.domain.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.test.StepVerifier;

@DataR2dbcTest
class AlumnoRepositoryTest {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @BeforeEach
    void setup() {
        alumnoRepository.deleteAll().block();
    }

    @Test
    void shouldInsertAndFindAlumnoByEstado() {

        // Given
        Long id = 1L;

        // When
        alumnoRepository.insertAlumno(id, "Manolo", "Perez", Estado.ACTIVO, 30).block();

        // Then
        StepVerifier.create(alumnoRepository.findByEstado(Estado.ACTIVO))
                .expectNextMatches(alumno ->
                        alumno.getId().equals(id) &&
                                alumno.getEstado() == Estado.ACTIVO
                )
                .verifyComplete();
    }

    @Test
    void shouldReturnEmptyWhenNoAlumnoWithEstado() {

        StepVerifier.create(alumnoRepository.findByEstado(Estado.INACTIVO))
                .expectNextCount(0)
                .verifyComplete();
    }
}