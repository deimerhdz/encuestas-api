package com.encuestas.services;

import com.encuestas.dto.EncuestadoWithRespuestaDto;
import com.encuestas.dto.ResultadoEncuestaDto;
import com.encuestas.entities.Encuestado;
import com.encuestas.entities.Respuesta;
import com.encuestas.exceptions.UniqueException;
import com.encuestas.repositories.EncuestadoRepository;
import com.encuestas.repositories.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncuestadoServiceImpl implements EncuestadoService{
    @Autowired
    EncuestadoRepository encuestadoRepository;

    @Autowired
    RespuestaRepository respuestaRepository;
    @Override
    public List<Encuestado> listar(Long idEncuesta) {

        return encuestadoRepository.findByIdEncuesta(idEncuesta);
    }

    @Override
    public Encuestado guardar(EncuestadoWithRespuestaDto encuestado) {
    List<Respuesta> respuestas = encuestado.getRespuestas();

        if(encuestadoRepository.existsByIdentificacion(encuestado.getEncuestado().getIdentificacion())){
            throw  new UniqueException("Ya has respondido la encuesta, no puedes volver a repetirla.");
        }
       Encuestado encuestadoDB= encuestadoRepository.save(encuestado.getEncuestado());

       for (int i=0;i<respuestas.size();i++){
            Respuesta resp = respuestas.get(i);
            resp.setIdEncuestado(encuestadoDB.getId());
           respuestaRepository.save(resp);
       }

        return encuestadoDB;
    }

    @Override
    public boolean existeEncuestado(String identificacion) {
        return encuestadoRepository.existsByIdentificacion(identificacion);
    }

    @Override
    public List<ResultadoEncuestaDto> listarEncuestadosPorCarrera(Long id) {
        return encuestadoRepository.listarEncuestadosPorCarrera(id);
    }
}
