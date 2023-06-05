package com.encuestas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "opciones")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Opcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_pregunta")
    private Long idPregunta;

    private String valor;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "id_pregunta",insertable = false,updatable = false)
    private Pregunta pregunta;
}
