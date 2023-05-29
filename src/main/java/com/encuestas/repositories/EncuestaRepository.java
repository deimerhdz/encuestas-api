package com.encuestas.repositories;

import com.encuestas.entities.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncuestaRepository extends JpaRepository<Encuesta,Long> {
    public List<Encuesta> findByIdUsuario(Long idUsuario);
}
