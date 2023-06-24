package com.encuestas.controllers;

import com.encuestas.dto.ResultadoEncuestaDto;
import com.encuestas.entities.Encuesta;
import com.encuestas.services.EncuestaService;
import com.encuestas.services.EncuestadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/encuestas")
public class EncuestaController {

    @Autowired
    private EncuestaService encuestaService;

    @Autowired
    private EncuestadoService encuestadoService;

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<List<Encuesta>> listar(@PathVariable Long idUsuario){
        List<Encuesta> encuestas =  encuestaService.listarEncuestas(idUsuario);
        return new ResponseEntity<>(encuestas, HttpStatus.OK);
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<Encuesta> obtenerPorId(@PathVariable Long id){
        Optional<Encuesta> encuesta =  encuestaService.obtenerEncuestaPorId(id);

        if(encuesta.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(encuesta.get(), HttpStatus.OK);
    }
    @GetMapping("/resultados/{id}")
    public ResponseEntity<List<ResultadoEncuestaDto>> resultadoEncuesta(@PathVariable Long id){
        return new ResponseEntity(encuestadoService.listarEncuestadosPorCarrera(id), HttpStatus.OK);
    }
    @GetMapping("/encuesta/{hash}")
    public ResponseEntity<Encuesta> encuesta(@PathVariable String hash){
        Optional<Encuesta> encuesta =  encuestaService.obtenerEncuesta(hash);

        if(!encuesta.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(encuesta.get(), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    ResponseEntity<Encuesta> guardar(@RequestBody Encuesta encuesta){
       Encuesta encuestaDB =  encuestaService.guardar(encuesta);
        return new ResponseEntity<>(encuestaDB, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    ResponseEntity<Encuesta> actualizar(@PathVariable Long id,@RequestBody Encuesta encuesta){
        Encuesta encuestaDB =  encuestaService.actualizar(id,encuesta);
        return new ResponseEntity<>(encuestaDB, HttpStatus.OK);
    }

    @PutMapping("/publicar/{id}")
    ResponseEntity<Boolean> publicar(@PathVariable Long id ){
       Boolean exito = encuestaService.publicarEncuesta(id);
        if(!exito){
            return new ResponseEntity<>(exito, HttpStatus.NOT_FOUND);
        };
        return new ResponseEntity<>(exito, HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    ResponseEntity<Boolean> eliminar(@PathVariable Long id ){
        Boolean exito =  encuestaService.eliminarEncuesta(id);
        if(!exito){
            return new ResponseEntity<>(exito, HttpStatus.NOT_FOUND);
        };
        return new ResponseEntity<>(exito, HttpStatus.OK);
    }


}
