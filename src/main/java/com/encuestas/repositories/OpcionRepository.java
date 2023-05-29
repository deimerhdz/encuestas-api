package com.encuestas.repositories;

import com.encuestas.entities.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcionRepository extends JpaRepository<Opcion,Long> {
}
