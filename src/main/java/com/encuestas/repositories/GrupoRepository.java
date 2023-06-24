package com.encuestas.repositories;

import com.encuestas.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo,Long> {

    void deleteByIdEncuesta(Long idEncuesta);
}
