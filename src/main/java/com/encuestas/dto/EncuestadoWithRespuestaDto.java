package com.encuestas.dto;

import com.encuestas.entities.Encuestado;
import com.encuestas.entities.Respuesta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncuestadoWithRespuestaDto {
    private Encuestado encuestado;
    private List<Respuesta> respuestas;
}
