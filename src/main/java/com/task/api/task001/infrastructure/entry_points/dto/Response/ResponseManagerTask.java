package com.task.api.task001.infrastructure.entry_points.dto.Response;

import java.time.LocalDate;

public record ResponseManagerTask(
        String title,
        String description,
        String status,
        LocalDate dateend
) {
}
