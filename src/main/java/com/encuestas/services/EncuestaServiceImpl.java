package com.encuestas.services;

import com.encuestas.entities.Encuesta;
import com.encuestas.repositories.EncuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncuestaServiceImpl implements EncuestaService{
    @Autowired
    EncuestaRepository encuestaRepository;
    @Override
    public List<Encuesta> listarEncuestas(Long idUsuario) {
        return encuestaRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public Optional<Encuesta> obtenerEncuestaPorId(Long id) {
        return encuestaRepository.findById(id);
    }

    @Override
    public Encuesta guardar(Encuesta encuesta) {
        return encuestaRepository.save(encuesta);
    }

    @Override
    public Encuesta actualizar(Long id, Encuesta encuesta) {
        Optional<Encuesta> encuestaDB = obtenerEncuestaPorId(id);
        Encuesta encuestaActualizada = null;
        if(encuestaDB.isPresent()){
            Encuesta  actualizarEncuesta = encuestaDB.get();

            actualizarEncuesta.setDescripcion(encuesta.getDescripcion());
            actualizarEncuesta.setTitulo(encuesta.getTitulo());
            actualizarEncuesta.setFechaFinal(encuesta.getFechaFinal());
            encuestaActualizada = guardar(actualizarEncuesta);
        }else{
            return  encuestaActualizada;
        }
        return encuestaActualizada;
    }

    @Override
    public boolean publicarEncuesta(Long id) {
        Optional<Encuesta> encuestaDB = obtenerEncuestaPorId(id);

        if(encuestaDB.isPresent()){
            Encuesta  actualizarEncuesta = encuestaDB.get();
            actualizarEncuesta.setEstado(!actualizarEncuesta.getEstado());
             guardar(actualizarEncuesta);
        }else{
            return  false;
        }
        return true;
    }

    @Override
    public boolean eliminarEncuesta(Long id) {
        Optional<Encuesta> encuestaDB = obtenerEncuestaPorId(id);

        if(encuestaDB.isPresent()){
            encuestaRepository.deleteById(id);
        }else{
            return  false;
        }
        return true;
    }
}
