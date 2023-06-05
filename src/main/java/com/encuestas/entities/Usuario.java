package com.encuestas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name = "usuarios")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String usuario;
    private String password;
    private String telefono;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    private Boolean estado;
    @PrePersist
    void prePersist(){
        this.estado = true;
        this.fechaCreacion = LocalDateTime.now();
    }
}
