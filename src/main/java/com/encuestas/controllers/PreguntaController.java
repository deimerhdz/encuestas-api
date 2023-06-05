package com.encuestas.controllers;
import com.encuestas.entities.Pregunta;
import com.encuestas.services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
    @Autowired
    private PreguntaService preguntaService;

    @GetMapping("/listar/{idEncuesta}")
    public ResponseEntity<List<Pregunta>> listar(@PathVariable Long idEncuesta){
        List<Pregunta> preguntas =  preguntaService.listarPreguntas(idEncuesta);
        return new ResponseEntity<>(preguntas, HttpStatus.OK);
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<Pregunta> obtenerPorId(@PathVariable Long id){
        Optional<Pregunta> pregunta =  preguntaService.obtenerPreguntaPorId(id);

        if(pregunta.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pregunta.get(), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    ResponseEntity<Pregunta> guardar(@RequestBody Pregunta pregunta){
        Pregunta preguntaDB =  preguntaService.guardarPregunta(pregunta);
        return new ResponseEntity<>(preguntaDB, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    ResponseEntity<Pregunta> actualizar(@PathVariable Long id,@RequestBody Pregunta pregunta){
        Pregunta preguntaDB =  preguntaService.actualizarPregunta(id,pregunta);
        return new ResponseEntity<>(preguntaDB, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    ResponseEntity<Boolean> eliminar(@PathVariable Long id ){
        Boolean exito =  preguntaService.eliminarPregunta(id);
        if(!exito){
            return new ResponseEntity<>(exito, HttpStatus.NOT_FOUND);
        };
        return new ResponseEntity<>(exito, HttpStatus.OK);
    }
}
