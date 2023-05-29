package com.encuestas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "encuestados")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Encuestado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idEncuesta;
    private String nombre;
    private String carrera;
    private String email;
    @Column(name = "fecha_realizacion")
    private LocalDateTime fechaRealizacion;
}
