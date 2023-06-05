package com.encuestas.services;

import com.encuestas.entities.Encuesta;
import com.encuestas.entities.Pregunta;
import com.encuestas.repositories.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PreguntaServiceImpl implements PreguntaService{
    @Autowired
    PreguntaRepository preguntaRepository;
    @Override
    public List<Pregunta> listarPreguntas(Long idEncuesta) {
        return preguntaRepository.findByIdEncuesta(idEncuesta);
    }

    @Override
    public Optional<Pregunta> obtenerPreguntaPorId(Long id) {
        return preguntaRepository.findById(id);
    }

    @Override
    public Pregunta guardarPregunta(Pregunta encuesta) {
        return preguntaRepository.save(encuesta);
    }

    @Override
    public Pregunta actualizarPregunta(Long id, Pregunta pregunta) {
        Optional<Pregunta> preguntaDB = obtenerPreguntaPorId(id);
        Pregunta preguntaActualizada = null;
        if(preguntaDB.isPresent()){
            Pregunta  actualizarPregunta = preguntaDB.get();

            actualizarPregunta.setTitulo(pregunta.getTitulo());
            actualizarPregunta.setIdTipoPregunta(pregunta.getIdTipoPregunta());

            preguntaActualizada = guardarPregunta(actualizarPregunta);
        }else{
            return  preguntaActualizada;
        }
        return preguntaActualizada;
    }

    @Override
    public boolean eliminarPregunta(Long id) {
        Optional<Pregunta> preguntaDB = obtenerPreguntaPorId(id);

        if(preguntaDB.isPresent()){
            preguntaRepository.deleteById(id);
        }else{
            return  false;
        }
        return true;
    }
}
