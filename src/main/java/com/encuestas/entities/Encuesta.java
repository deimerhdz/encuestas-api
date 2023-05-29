package com.encuestas.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private String titulo;

    private String descripcion;

    private Boolean estado;
    @Column(name="fecha_inicio")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaRegistro;

    @Column(name="fecha_final")

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaFinal;

    @PrePersist
    public void prePersist(){
        this.fechaRegistro = LocalDateTime.now();
        this.estado =false;
    }

}
