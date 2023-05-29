package com.encuestas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "preguntas")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pregunta {

    @Id
    @Column(name="codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private Long idEncuesta;
    private String titulo;
    private Long idTipoPregunta;



}
