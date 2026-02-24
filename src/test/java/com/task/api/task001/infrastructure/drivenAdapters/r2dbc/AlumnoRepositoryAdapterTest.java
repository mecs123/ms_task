package com.task.api.task001.infrastructure.drivenAdapters.r2dbc;

import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.model.Alumno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import(AlumnoRepositoryAdapter.class)
class AlumnoRepositoryAdapterTest {

    @Autowired
    private R2dbcEntityTemplate template;

    @Autowired
    private AlumnoRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        template.getDatabaseClient()
                .sql("DELETE FROM alumno")
                .then()
                .block();
    }

    @Test
    void save_debeInsertarAlumno() {

        Alumno alumno = new Alumno(1L, "Manolo", "Eduardo", Estado.ACTIVO, 25);

        Mono<Alumno> result = adapter.save(alumno);

        StepVerifier.create(result)
                .expectNextMatches(a -> a.getId().equals(1L))
                .verifyComplete();
    }

    @Test
    void existsById_debeRetornarTrueSiExiste() {

        Alumno alumno = new Alumno(1L, "Manolo", "Eduardo", Estado.ACTIVO, 25);
        adapter.save(alumno).block();

        StepVerifier.create(adapter.existsById(1L))
                .expectNext(true)
                .verifyComplete();
    }
}