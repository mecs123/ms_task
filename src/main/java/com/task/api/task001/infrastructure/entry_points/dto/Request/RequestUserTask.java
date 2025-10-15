package com.task.api.task001.infrastructure.entry_points.dto.Request;

import org.springframework.validation.annotation.Validated;

public record RequestUserTask(
        @Validated
        String name,
        @Validated
        String email
) {
}
