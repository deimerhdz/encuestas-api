package com.encuestas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(name="id_encuesta")
    private Long idEncuesta;

    private String titulo;

    @Column(name="id_tipo_pregunta")
    private Long idTipoPregunta;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private List<Opcion> opciones;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "id_encuesta",insertable = false,updatable = false)
    private Encuesta encuesta;

}
