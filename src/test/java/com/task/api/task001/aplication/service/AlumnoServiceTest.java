package com.task.api.task001.aplication.service;

import com.task.api.task001.aplication.port.out.AlumnoRepositoryPort;
import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.model.Alumno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AlumnoServiceTest {

    @Mock
    private AlumnoRepositoryPort alumnoRepositoryPort;

    private AlumnoService alumnoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alumnoService = new AlumnoService(alumnoRepositoryPort);
    }


    @Test
    void crearAlumno_debeGuardarCuandoNoExiste() {

        Alumno alumnoRequest = new Alumno(1L, "Manolo", "Castro",  Estado.ACTIVO,32);

        when(alumnoRepositoryPort.existsById(1L)).thenReturn(Mono.just(false));
        when(alumnoRepositoryPort.save(alumnoRequest)).thenReturn(Mono.just(alumnoRequest));
        Mono<Void> resultado = alumnoService.crearAlumno(alumnoRequest);
        StepVerifier.create(resultado).verifyComplete();
        verify(alumnoRepositoryPort).existsById(1L);
        verify(alumnoRepositoryPort).save(alumnoRequest);
    }

    @Test
    void crear_alumno_lanzaerror_cuando_existe(){
        Alumno alumno = new Alumno(1L, "Manolo", "Castro",  Estado.ACTIVO,32);
        when(alumnoRepositoryPort.existsById(1L)).thenReturn(Mono.just(true));
        Mono<Void> resultado = alumnoService.crearAlumno(alumno);
        StepVerifier.create(resultado)
                .expectError()
                .verify();
        verify(alumnoRepositoryPort).existsById(1L);
        verify(alumnoRepositoryPort, never()).save(any());
    }

    @Test
    void obtner_alumnos_activos_lista(){
        Alumno alumno1 = new Alumno(1L, "Manolo", "Castro",  Estado.ACTIVO,32);
        Alumno alumno2 = new Alumno(2L, "Eduardo", "Santana",  Estado.ACTIVO,32);

        when(alumnoRepositoryPort.getAll(Estado.ACTIVO)).thenReturn(Flux.just(alumno1,alumno2));
        Flux<Alumno> resultado = alumnoService.obtenerActivos();
        StepVerifier.create(resultado)
                .expectNext(alumno1)
                .expectNext(alumno2)
                .verifyComplete();
        verify(alumnoRepositoryPort).getAll(Estado.ACTIVO);
    }
}