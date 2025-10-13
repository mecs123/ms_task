package com.task.api.task001.aplication.dto;

import java.time.LocalDate;
import java.util.Date;

public record ManagerTaskDTO(
        String title,
        String description,
        String status,
        LocalDate dateend
) {
}
