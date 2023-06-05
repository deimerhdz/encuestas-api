package com.encuestas.services;

import com.encuestas.entities.Encuesta;

import java.util.List;
import java.util.Optional;

public interface EncuestaService {
    List<Encuesta> listarEncuestas(Long idUsuario);

    Optional<Encuesta> obtenerEncuestaPorId(Long id);
    Optional<Encuesta> obtenerEncuesta(String  hash);
    Encuesta  guardar(Encuesta encuesta);

    Encuesta actualizar(Long id, Encuesta encuesta);

    boolean publicarEncuesta(Long id);

    boolean eliminarEncuesta(Long id);
}
