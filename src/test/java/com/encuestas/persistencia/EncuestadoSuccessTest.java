package com.encuestas.persistencia;

import com.encuestas.entities.Encuesta;
import com.encuestas.entities.Encuestado;
import com.encuestas.repositories.EncuestaRepository;
import com.encuestas.repositories.EncuestadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class EncuestadoSuccessTest {
    @Autowired
    EncuestadoRepository encuestadoRepository;

    @Autowired
    EncuestaRepository encuestaRepository;
    private Encuesta encuesta;
    private Encuestado encuestado;

    @BeforeEach
    void setup(){
 ;
    }

    @DisplayName("Test para guardar un encuestado")
    @Test
    public void guaradarEncuestado(){
        //given
        encuesta = new Encuesta(null,1l,"kdsfkfs","encuesta de satisfaccion","test descripcion",false,null,null, LocalDateTime.now(),LocalDateTime.now());
        encuesta =  encuestaRepository.save(encuesta);
        encuestado = new Encuestado(1l,"1234",encuesta.getId(),"Jose Peres","Ingenieria de sistemas","jose@email.com",LocalDateTime.now());
        //when
        Encuestado encuestadoGuardado = encuestadoRepository.save(encuestado);
        //then
        assertThat(encuestadoGuardado).isNotNull();
        assertThat(encuestadoGuardado.getId()).isGreaterThan(0L);
    }


    @Test
    public void obtenerEncuestadosPorCarrera(){

    }
}
