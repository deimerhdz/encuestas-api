package com.encuestas.persistencia;

import com.encuestas.entities.Encuesta;
import com.encuestas.entities.Pregunta;
import com.encuestas.entities.TipoPregunta;
import com.encuestas.repositories.EncuestaRepository;
import com.encuestas.repositories.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.Option;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PreguntaSuccessTest {
    @Autowired
    PreguntaRepository preguntaRepository;
    @Autowired
    EncuestaRepository encuestaRepository;
    private Encuesta encuesta;
    private TipoPregunta tipoPregunta;
    private Pregunta pregunta;
    @BeforeEach
    void setup(){
        encuesta = new Encuesta(null,1l,"kdsfkfs","encuesta de satisfaccion","test descripcion",false,null, LocalDateTime.now(),LocalDateTime.now());
        encuesta = encuestaRepository.save(encuesta);
        tipoPregunta = new TipoPregunta(1l,"Opcion Multiple", "preguntas de opcion multiple");
        pregunta = new Pregunta(null,encuesta.getId(),"pregunta2",tipoPregunta.getId(), null,null);

    }
    @DisplayName("Test para guardar pregunta")
    @Test
    public void testGuardarPregunta(){
        //given

        Pregunta pregunta = new Pregunta(1l,encuesta.getId(),"pregunta1",tipoPregunta.getId(),null,null);
        //when
        Pregunta preguntaGuardada = preguntaRepository.save(pregunta);

        //then
        assertThat(preguntaGuardada).isNotNull();
        assertThat(preguntaGuardada.getCodigo()).isGreaterThan(0L);
    }
    @Test
    public void testListarPreguntas(){
        //given

        Pregunta pregunta1 = new Pregunta(3l,encuesta.getId(),"pregunta1",tipoPregunta.getId(), null,null);
        Pregunta pregunta2 = new Pregunta(3l,encuesta.getId(),"pregunta2",tipoPregunta.getId(), null,null);

        preguntaRepository.save(pregunta1);
         preguntaRepository.save( pregunta2);
        //when
       List<Pregunta> preguntas = preguntaRepository.findByIdEncuesta(encuesta.getId());

       //then
        assertThat(preguntas).isNotNull();
        assertThat(preguntas.size()).isEqualTo(2);
    }
    @DisplayName("Test para obtener una pregunta por id")
    @Test
    public void testObtenerPreguntaPorId(){
        //give
        pregunta = preguntaRepository.save(pregunta);
        //when
        Pregunta preguntaDB = preguntaRepository.findById(pregunta.getCodigo()).get();
        //then
        assertThat(preguntaDB).isNotNull();
    }

    /*Test
    public void testActualizarPregunta(){
         //give
        // when
        //them
    }*/
    @DisplayName("Test eliminar pregunta")
    @Test
    public void eliminarPregunta(){
        //give
        pregunta = preguntaRepository.save(pregunta);
        // when
        preguntaRepository.deleteById(pregunta.getCodigo());
        Optional<Pregunta> preguntaOptional = preguntaRepository.findById(pregunta.getCodigo());
        //them
        assertThat(preguntaOptional).isEmpty();
    }
}
