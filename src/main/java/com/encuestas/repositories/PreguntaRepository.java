package com.encuestas.repositories;

import com.encuestas.entities.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<Pregunta,Long> {
}
