package com.task.api.task001.infrastructure.entryPoints;

import com.task.api.task001.infrastructure.entryPoints.handler.AlumnoHandler;
import com.task.api.task001.infrastructure.entryPoints.handler.ManagerTaskHandler;
import com.task.api.task001.infrastructure.entryPoints.handler.UserTaskHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.mockito.Mockito.*;

class RouterRestTest {

    private WebTestClient webTestClient;

    private AlumnoHandler alumnoHandler;
    private UserTaskHandler userTaskHandler;
    private ManagerTaskHandler managerTaskHandler;

    @BeforeEach
    void setUp() {

        alumnoHandler = mock(AlumnoHandler.class);
        userTaskHandler = mock(UserTaskHandler.class);
        managerTaskHandler = mock(ManagerTaskHandler.class);

        RouterRest routerRest = new RouterRest();

        RouterFunction<ServerResponse> routerFunction =
                routerRest.route(userTaskHandler, managerTaskHandler, alumnoHandler);

        webTestClient = WebTestClient.bindToRouterFunction(routerFunction)
                .build();
    }

    @Test
    void debeRutarPostAlumnos() {

        when(alumnoHandler.crearAlumno(any()))
                .thenReturn(ServerResponse.ok().build());

        webTestClient.post()
                .uri("/alumnos")
                .exchange()
                .expectStatus().isOk();

        verify(alumnoHandler).crearAlumno(any());
    }
    @Test
    void debeRutarGetAlumnosActivos() {

        when(alumnoHandler.obtenerActivos(any())).thenReturn(ServerResponse.ok().build());

        webTestClient.get()
                .uri("/alumnos/activos")
                .exchange()
                .expectStatus().isOk();

        verify(alumnoHandler).obtenerActivos(any());
    }


}