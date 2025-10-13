package com.task.api.task001.domain.model;

import java.time.LocalDate;

public record ManagerTask(
        String title,
        String description,
        String status,
        LocalDate dateend

) {
}
