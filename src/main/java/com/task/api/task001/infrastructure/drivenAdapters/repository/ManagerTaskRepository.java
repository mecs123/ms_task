package com.task.api.task001.infrastructure.drivenAdapters.repository;

import com.task.api.task001.domain.model.ManagerTask;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ManagerTaskRepository extends ReactiveCrudRepository<ManagerTask, Long> {

    @Query("""
            INSERT INTO ManagerTask (title, description, status, dateend)
            VALUES (:title, :description, :status, :dateEnd)
            RETURNING *
            """)
    Mono<ManagerTask> createTask(
            String title,
            String description,
            String status,
            LocalDate dateEnd
    );

    @Query("SELECT * FROM ManagerTask WHERE id = :id")
    Mono<ManagerTask> findById(Long id);

    @Query("SELECT * FROM ManagerTask")
    Flux<ManagerTask> findAllTasks();

    @Query("""
            UPDATE ManagerTask
            SET status = 'COMPLETADA'
            WHERE id = :id
            RETURNING *
            """)
    Mono<ManagerTask> completeTask(Long id);

    @Query("DELETE FROM ManagerTask WHERE id = :id RETURNING *")
    Mono<ManagerTask> deleteTask(Long id);
}
