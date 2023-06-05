package com.encuestas.repositories;

import com.encuestas.entities.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta,Long> {

    List<Pregunta> findByIdEncuesta(Long idEncuesta);
}
