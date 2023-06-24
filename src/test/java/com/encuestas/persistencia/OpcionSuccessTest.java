package com.encuestas.persistencia;

import com.encuestas.entities.*;
import com.encuestas.repositories.EncuestaRepository;
import com.encuestas.repositories.OpcionRepository;
import com.encuestas.repositories.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class OpcionSuccessTest {
    @Autowired
    OpcionRepository opcionRepository;

    @Autowired
    EncuestaRepository encuestaRepository;
    @Autowired
    PreguntaRepository preguntaRepository;
    private Encuesta encuesta;
    private TipoPregunta tipoPregunta;
    private Pregunta pregunta;
    private Opcion opcion;
    @BeforeEach
    void setup(){
         encuesta = new Encuesta(null,1l,"kdsfkfs","encuesta de satisfaccion","test descripcion",false,null,null, LocalDateTime.now(),LocalDateTime.now());
         encuesta = encuestaRepository.save(encuesta);
         tipoPregunta = new TipoPregunta(1l,"Opcion Multiple", "preguntas de opcion multiple");
         pregunta = new Pregunta(null,encuesta.getId(),"pregunta1",tipoPregunta.getId(),null,null);
         pregunta = preguntaRepository.save(pregunta);
         opcion = new Opcion(1l,pregunta.getCodigo(),"Opcion1",null);
    }

    @DisplayName("Test para guardar una opcion")
    @Test
    public void testGuardarOpcion(){
            //given
        Opcion opcion1 = new Opcion(1l,pregunta.getCodigo(),"Opcion1",null);
            //when
       Opcion opcionGuardada =  opcionRepository.save(opcion1);
            //then
        assertThat(opcionGuardada).isNotNull();
        assertThat(opcionGuardada.getId()).isGreaterThan(0l);

    }
    @Test
    public void testListarOpciones(){
        //given

        Opcion opcion1 = new Opcion(1l,pregunta.getCodigo(),"Opcion2",null);
        opcionRepository.save(opcion1);
        opcionRepository.save(opcion);
        //when
        List<Opcion> opciones = opcionRepository.findByIdPregunta(pregunta.getCodigo());
        //then
        assertThat(opciones).isNotNull();
        assertThat(opciones.size()).isEqualTo(2);
    }
    @Test
    public void testObtenerOpcionPorId(){

    }

    @Test
    public void testActualizarOpcion(){

    }
    @Test
    public void eliminarOpcion(){

    }
}
