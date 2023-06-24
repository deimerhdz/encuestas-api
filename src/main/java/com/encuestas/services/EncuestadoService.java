package com.encuestas.services;

import com.encuestas.dto.EncuestadoWithRespuestaDto;
import com.encuestas.dto.ResultadoEncuestaDto;
import com.encuestas.entities.Encuestado;

import java.util.List;

public interface EncuestadoService {
    List<Encuestado> listar(Long idEncuesta);

    Encuestado guardar(EncuestadoWithRespuestaDto encuestado);

    public boolean existeEncuestado(String identificacion);
    List<ResultadoEncuestaDto> listarEncuestadosPorCarrera(Long id);
}
