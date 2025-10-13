package com.task.api.task001.infrastructure.driven_adapters.repository;

import com.task.api.task001.domain.model.UserTask;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserTaskRespository extends ReactiveCrudRepository<UserTask, Long> {

    @Query("""
            INSERT INTO UserTask (name, email)
            VALUES (:name, :email)
            RETURNING *
            """)
    Mono<UserTask> saveUserTask(String name, String email);

    @Query("SELECT * FROM UserTask WHERE id = :id")
    Optional<UserTask> getById(Long id);
}
