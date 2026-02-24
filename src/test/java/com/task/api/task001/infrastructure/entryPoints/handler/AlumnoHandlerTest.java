package com.task.api.task001.infrastructure.entryPoints.handler;

import com.task.api.task001.aplication.port.in.AlumnoCase;
import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.exceptions.user.AlumnoAlreadyExistsException;
import com.task.api.task001.domain.model.Alumno;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestAlumnoDTO;
import com.task.api.task001.infrastructure.entryPoints.mapper.AlumnoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AlumnoHandlerTest {

    private WebTestClient webTestClient;

    private AlumnoCase alumnoCase;
    private AlumnoMapper alumnoMapper;

    @BeforeEach
    void setUp() {

        alumnoCase = mock(AlumnoCase.class);
        alumnoMapper = mock(AlumnoMapper.class);

        AlumnoHandler handler = new AlumnoHandler(alumnoCase, alumnoMapper);

        RouterFunction<ServerResponse> router =
                RouterFunctions.route()
                        .POST("/alumnos", handler::crearAlumno)
                        .GET("/alumnos/activos", handler::obtenerActivos)
                        .build();

        webTestClient = WebTestClient.bindToRouterFunction(router).build();
    }

    @Test
    void crearAlumno_debeRetornar201() {

        RequestAlumnoDTO requestDTO = new RequestAlumnoDTO(1L, "Manolo", "Castro", Estado.ACTIVO, 25);
        Alumno alumno = new Alumno(1L, "Manolo", "Castro", Estado.ACTIVO, 25);

        when(alumnoMapper.toDomain(any())).thenReturn(alumno);
        when(alumnoCase.crearAlumno(alumno)).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/alumnos")
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated();

        verify(alumnoCase).crearAlumno(alumno);
    }


    @Test
    void crearAlumno_debeRetornar409_siYaExiste() {

        RequestAlumnoDTO requestDTO = new RequestAlumnoDTO(1L, "Manolo", "Castro", Estado.ACTIVO, 25);
        Alumno alumno = new Alumno(1L, "Manolo", "Castro", Estado.ACTIVO, 25);

        when(alumnoMapper.toDomain(any())).thenReturn(alumno);
        when(alumnoCase.crearAlumno(alumno))
                .thenReturn(Mono.error(new AlumnoAlreadyExistsException("Ya existe")));

        webTestClient.post()
                .uri("/alumnos")
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isEqualTo(409);

        verify(alumnoCase).crearAlumno(alumno);
    }

    @Test
    void obtenerActivos_debeRetornarLista() {

        Alumno alumno1 = new Alumno(1L, "Manolo", "Castro", Estado.ACTIVO, 25);
        Alumno alumno2 = new Alumno(2L, "Manolo", "Castro", Estado.ACTIVO, 25);

        when(alumnoCase.obtenerActivos()).thenReturn(Flux.just(alumno1, alumno2));

        webTestClient.get()
                .uri("/alumnos/activos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Alumno.class)
                .hasSize(2);
    }

    @Test
    void crearAlumno_debeRetornar500_conMensajeCuandoErrorGenerico() {

        RequestAlumnoDTO requestDTO = new RequestAlumnoDTO(1L, "Manolo", "Castro", Estado.ACTIVO, 25);
        Alumno alumno = new Alumno(1L, "Manolo", "Castro", Estado.ACTIVO, 25);

        when(alumnoMapper.toDomain(any())).thenReturn(alumno);
        when(alumnoCase.crearAlumno(alumno)).thenReturn(Mono.error(new RuntimeException("DB error")));

        webTestClient.post()
                .uri("/alumnos")
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class)
                .value(body ->
                        assertEquals("Unexpected error: DB error", body)
                );
    }

}