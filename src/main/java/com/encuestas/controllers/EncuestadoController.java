package com.encuestas.controllers;

import com.encuestas.dto.EncuestadoWithRespuestaDto;
import com.encuestas.dto.ResultadoEncuestaDto;
import com.encuestas.entities.Encuestado;
import com.encuestas.services.EncuestadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encuestado")
public class EncuestadoController {

    @Autowired
    private EncuestadoService encuestadoService;

    @GetMapping("/listado/{idEncuesta}")
    public ResponseEntity<List<Encuestado>> listarEncuestado(@PathVariable("idEncuesta") Long idEncuesta){
        return new ResponseEntity(encuestadoService.listar(idEncuesta), HttpStatus.OK);
    }
    @GetMapping("/verificar/{identificacion}")
    public ResponseEntity<Boolean> listarEncuestado(@PathVariable("identificacion") String identificacion){
        return new ResponseEntity(encuestadoService.existeEncuestado(identificacion), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Encuestado> guardar(@RequestBody EncuestadoWithRespuestaDto encuestado){

        return new ResponseEntity(encuestadoService.guardar(encuestado), HttpStatus.OK);
    }


}
