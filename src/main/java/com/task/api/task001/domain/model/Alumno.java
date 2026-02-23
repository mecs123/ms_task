package com.task.api.task001.domain.model;

import com.task.api.task001.domain.Estado;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("alumno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno implements Persistable<Long> {

    @Id
    private Long id;

    private String nombre;
    private String apellido;
    private Estado estado;
    private Integer edad;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return true; // Fuerza a que siempre sea INSERT
    }
}