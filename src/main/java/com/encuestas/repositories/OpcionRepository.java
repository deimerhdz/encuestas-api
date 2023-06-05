package com.encuestas.repositories;

import com.encuestas.entities.Opcion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpcionRepository extends JpaRepository<Opcion,Long> {
    List<Opcion> findByIdPregunta(Long idPregunta);
}
