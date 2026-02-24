package com.task.api.task001.infrastructure.entryPoints.mapper;

import com.task.api.task001.domain.Estado;
import com.task.api.task001.domain.model.Alumno;
import com.task.api.task001.infrastructure.entryPoints.dto.Request.RequestAlumnoDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class AlumnoMapperTest {

    private final AlumnoMapper mapper =
            Mappers.getMapper(AlumnoMapper.class);

    @Test
    void toDomain_debeMapearCorrectamente() {

        // Arrange
        RequestAlumnoDTO dto =
                new RequestAlumnoDTO(1L, "Manolo", "Eduardo", Estado.ACTIVO, 25);

        // Act
        Alumno alumno = mapper.toDomain(dto);

        // Assert
        assertNotNull(alumno);
        assertEquals(1L, alumno.getId());
        assertEquals("Manolo", alumno.getNombre());
        assertEquals("Eduardo", alumno.getApellido());
        assertEquals(Estado.ACTIVO, alumno.getEstado());
        assertEquals(25, alumno.getEdad());
    }
}