package com.encuestas.repositories;

import com.encuestas.entities.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EncuestaRepository extends JpaRepository<Encuesta,Long> {
    public List<Encuesta> findByIdUsuario(Long idUsuario);


    public Optional<Encuesta> findByHash(String hash);
}
