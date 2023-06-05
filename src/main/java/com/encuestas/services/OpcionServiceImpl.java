package com.encuestas.services;


import com.encuestas.entities.Opcion;
import com.encuestas.repositories.OpcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OpcionServiceImpl implements OpcionService{
    @Autowired
    OpcionRepository opcionRepository;
    @Override
    public List<Opcion> listarOpcions(Long idPregunta) {
        return opcionRepository.findByIdPregunta(idPregunta);
    }

    @Override
    public Optional<Opcion> obtenerOpcionPorId(Long id) {
        return opcionRepository.findById(id);
    }

    @Override
    public Opcion guardarOpcion(Opcion opcion) {
        return opcionRepository.save(opcion);
    }

    @Override
    public Opcion actualizarOpcion(Long id, Opcion opcion) {
        Optional<Opcion> opcionDB = obtenerOpcionPorId(id);
        Opcion opcionActualizada = null;
        if(opcionDB.isPresent()){
            Opcion  actualizarPregunta = opcionDB.get();

            actualizarPregunta.setValor(opcion.getValor());

            opcionActualizada = guardarOpcion(actualizarPregunta);
        }else{
            return  opcionActualizada;
        }
        return opcionActualizada;
    }

    @Override
    public boolean eliminarOpcion(Long id) {
        Optional<Opcion> opcionDB = obtenerOpcionPorId(id);

        if(opcionDB.isPresent()){
            opcionRepository.deleteById(id);
        }else{
            return  false;
        }
        return true;
    }
}
