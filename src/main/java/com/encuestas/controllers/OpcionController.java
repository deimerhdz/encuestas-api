package com.encuestas.controllers;

import com.encuestas.entities.Opcion;
import com.encuestas.entities.Pregunta;
import com.encuestas.services.OpcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/opciones")
public class OpcionController {
    @Autowired
    OpcionService opcionService;

    @GetMapping("/listar/{idPregunta}")
    public ResponseEntity<List<Opcion>> listar(@PathVariable Long idPregunta){
        List<Opcion> preguntas =  opcionService.listarOpcions(idPregunta);
        return new ResponseEntity<>(preguntas, HttpStatus.OK);
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<Opcion> obtenerPorId(@PathVariable Long id){
        Optional<Opcion> pregunta =  opcionService.obtenerOpcionPorId(id);

        if(pregunta.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pregunta.get(), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    ResponseEntity<Opcion> guardar(@RequestBody Opcion opcion){
        Opcion opcionDB =  opcionService.guardarOpcion(opcion);
        return new ResponseEntity<>(opcionDB, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    ResponseEntity<Opcion> actualizar(@PathVariable Long id, @RequestBody Opcion opcion){
        Opcion preguntaDB =  opcionService.actualizarOpcion(id,opcion);
        return new ResponseEntity<>(preguntaDB, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    ResponseEntity<Boolean> eliminar(@PathVariable Long id ){
        Boolean exito =  opcionService.eliminarOpcion(id);
        if(!exito){
            return new ResponseEntity<>(exito, HttpStatus.NOT_FOUND);
        };
        return new ResponseEntity<>(exito, HttpStatus.OK);
    }
}
