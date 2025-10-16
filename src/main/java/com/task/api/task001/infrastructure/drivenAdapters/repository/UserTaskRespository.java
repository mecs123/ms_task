package com.task.api.task001.infrastructure.drivenAdapters.repository;

import com.task.api.task001.domain.model.UserTask;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserTaskRespository extends ReactiveCrudRepository<UserTask, Long> {

    @Query("""
            INSERT INTO UserTask (name, email)
            VALUES (:name, :email)
            RETURNING *
            """)
    Mono<UserTask> saveUserTask(String name, String email);

    @Query("SELECT * FROM UserTask WHERE email = :email")
    Mono<UserTask> getByEmail(String email);
}
