package com.encuestas.services;

import com.encuestas.entities.Opcion;
import com.encuestas.entities.Pregunta;

import java.util.List;
import java.util.Optional;

public interface OpcionService {
    List<Opcion> listarOpcions(Long idPregunta);

    Optional<Opcion> obtenerOpcionPorId(Long id);

    Opcion  guardarOpcion(Opcion opcion);

    Opcion actualizarOpcion(Long id, Opcion opcion);

    boolean eliminarOpcion(Long id);
}
