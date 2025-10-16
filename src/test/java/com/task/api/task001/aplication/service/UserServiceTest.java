package com.task.api.task001.aplication.service;

import com.task.api.task001.aplication.port.out.UserRepositoryPort;
import com.task.api.task001.domain.exceptions.user.UserAlreadyExistsException;
import com.task.api.task001.domain.model.UserTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserService userService;

    private UserTask userTask;

    @BeforeEach
    void setUp() {
        userTask = new UserTask("John Doe", "john@example.com");
    }

    @Test
    void createUser_ShouldSave_WhenEmailNotExists() {
        // given
        when(userRepositoryPort.getByEmail(userTask.email())).thenReturn(Mono.empty());
        when(userRepositoryPort.saveUser(userTask)).thenReturn(Mono.just(userTask));

        // when
        Mono<UserTask> result = userService.createUser(userTask);

        // then
        StepVerifier.create(result)
                .expectNext(userTask)
                .verifyComplete();

        verify(userRepositoryPort).getByEmail(userTask.email());
        verify(userRepositoryPort).saveUser(userTask);
    }

    @Test
    void createUser_ShouldError_WhenEmailExists() {
        // given
        when(userRepositoryPort.getByEmail(userTask.email())).thenReturn(Mono.just(userTask));

        // when
        Mono<UserTask> result = userService.createUser(userTask);

        // then
        StepVerifier.create(result)
                .expectError(UserAlreadyExistsException.class)
                .verify();

        verify(userRepositoryPort, never()).saveUser(any());
    }

    @Test
    void validateEmail_ShouldReturnTrue_WhenUserExists() {
        when(userRepositoryPort.getByEmail("john@example.com")).thenReturn(Mono.just(userTask));

        StepVerifier.create(userService.validateEmail("john@example.com"))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void validateEmail_ShouldReturnFalse_WhenUserNotExists() {
        when(userRepositoryPort.getByEmail("noone@example.com")).thenReturn(Mono.empty());

        StepVerifier.create(userService.validateEmail("noone@example.com"))
                .expectNext(false)
                .verifyComplete();
    }
}
