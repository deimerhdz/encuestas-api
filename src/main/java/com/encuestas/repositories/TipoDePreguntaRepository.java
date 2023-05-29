package com.encuestas.repositories;

import com.encuestas.entities.TipoPregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDePreguntaRepository extends JpaRepository<TipoPregunta,Long> {
}
