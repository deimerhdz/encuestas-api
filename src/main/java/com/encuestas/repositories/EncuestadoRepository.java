package com.encuestas.repositories;

import com.encuestas.entities.Encuesta;
import com.encuestas.entities.Encuestado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncuestadoRepository extends JpaRepository<Encuestado,Long> {


}
