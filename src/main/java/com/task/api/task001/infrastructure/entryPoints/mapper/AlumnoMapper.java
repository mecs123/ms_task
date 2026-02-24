package com.task.api.task001.infrastructure.entryPoints.mapper;

import com.task.api.task001.domain.model.Alumno;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestAlumnoDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface AlumnoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "apellido", source = "apellido")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "edad", source = "edad")
    Alumno toDomain(RequestAlumnoDTO requestAlumno);

}
