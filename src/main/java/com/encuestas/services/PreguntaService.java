package com.encuestas.services;

import com.encuestas.entities.Encuesta;
import com.encuestas.entities.Pregunta;

import java.util.List;
import java.util.Optional;

public interface PreguntaService {

    List<Pregunta> listarPreguntas(Long idEncuesta);

    Optional<Pregunta> obtenerPreguntaPorId(Long id);

    Pregunta  guardarPregunta(Pregunta encuesta);

    Pregunta actualizarPregunta(Long id, Pregunta encuesta);

    boolean eliminarPregunta(Long id);

}
