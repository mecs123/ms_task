package com.task.api.task001.domain.model;

import com.task.api.task001.domain.Estado;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("alumno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

    @Id
    private Long id;

    private String nombre;
    private String apellido;
    private Estado estado;
    private Integer edad;

}