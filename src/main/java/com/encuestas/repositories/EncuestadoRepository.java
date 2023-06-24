package com.encuestas.repositories;


import com.encuestas.dto.ResultadoEncuestaDto;
import com.encuestas.entities.Encuestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EncuestadoRepository extends JpaRepository<Encuestado,Long> {
    public List<Encuestado> findByIdEncuesta(Long idEncuestado);
    public boolean existsByIdentificacion(String identificacion);
    @Query("SELECT new com.encuestas.dto.ResultadoEncuestaDto(e.carrera,COUNT(*)) From Encuestado as e WHERE e.idEncuesta = :idEncuesta GROUP BY e.carrera")
     public List<ResultadoEncuestaDto> listarEncuestadosPorCarrera(@Param("idEncuesta") Long idEncuesta);
}
