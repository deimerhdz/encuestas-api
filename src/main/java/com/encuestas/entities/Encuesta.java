package com.encuestas.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "encuestas")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    private String hash;

    private String titulo;

    private String descripcion;

    private Boolean estado;

    @OneToMany(mappedBy = "encuesta",orphanRemoval = true)
    private List<Pregunta> preguntas;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "encuesta")
    private List<Grupo> grupos;

    @Column(name="fecha_inicio")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaRegistro;

    @Column(name="fecha_final")

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaFinal;

    @PrePersist
    public void prePersist(){
        UUID uuid = UUID.randomUUID();
        this.hash = uuid.toString();
        this.fechaRegistro = LocalDateTime.now();
        this.estado =false;
    }


}
