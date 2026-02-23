package com.task.api.task001.infrastructure.entryPoints.dto.Response;

import com.task.api.task001.domain.Estado;

public record ResponseAlumnoDTO(
        Long id,
        String nombre,
        String apellido,
        Estado estado,
        Integer edad
) {}