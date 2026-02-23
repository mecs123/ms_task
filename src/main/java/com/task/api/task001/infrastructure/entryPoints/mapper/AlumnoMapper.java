package com.task.api.task001.infrastructure.entryPoints.mapper;

import com.task.api.task001.domain.model.Alumno;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestAlumnoDTO;
import com.task.api.task001.infrastructure.entryPoints.dto.Response.ResponseAlumnoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

    AlumnoMapper INSTANCE = Mappers.getMapper(AlumnoMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "apellido", source = "apellido")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "edad", source = "edad")
    Alumno toDomain(RequestAlumnoDTO requestAlumno);

    ResponseAlumnoDTO toResponse(Alumno alumno);
}
