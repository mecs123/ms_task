package com.task.api.task001.infrastructure.entryPoints.dto.Request;

import java.time.LocalDate;

public record RequestManagerTask(
        String title,
        String description,
        String status,
        LocalDate dateend
) {
}
