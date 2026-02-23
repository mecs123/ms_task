package com.task.api.task001.infrastructure.entryPoints.dto.Request;

import com.task.api.task001.domain.Estado;

public record RequestAlumnoDTO(
        Long id,
        String nombre,
        String apellido,
        Estado estado,
        Integer edad
) {}
