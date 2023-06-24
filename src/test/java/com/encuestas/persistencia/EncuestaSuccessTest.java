package com.encuestas.persistencia;

import com.encuestas.entities.Encuesta;
import com.encuestas.repositories.EncuestaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class EncuestaSuccessTest {
    @Autowired
    EncuestaRepository encuestaRepository;

    private Encuesta encuesta;

    @BeforeEach
    void setup(){
        encuesta = new Encuesta(1l,1l,"kdsfkfs","encuesta de satisfaccion","test descripcion",false,null, LocalDateTime.now(),LocalDateTime.now());
    }
    @DisplayName("Test para guardar una encuesta")
    @Test
    public void testGuardarEncuesta(){
        //given
        Encuesta encuesta = new Encuesta(1l,1l,"kdsfkfs","encuesta de satisfaccion","test descripcion",false,null, LocalDateTime.now(),LocalDateTime.now());
        //when
        Encuesta encuestaGuardada = encuestaRepository.save(encuesta);
        //then
        assertThat(encuestaGuardada).isNotNull();
        assertThat(encuestaGuardada.getId()).isGreaterThan(0l);
    }

    @DisplayName("Test para listar las encuestas")
    @Test
    public void testListarEncuesta(){
        //given
        Encuesta encuesta1 = new Encuesta(2l,1l,"kdsfkfs","encuesta de satisfaccion","test descripcion",false,null, LocalDateTime.now(),LocalDateTime.now());
        encuestaRepository.save(encuesta);
        encuestaRepository.save(encuesta1);
        //when
        List<Encuesta> empleados = encuestaRepository.findByIdUsuario(1l);
        //then
        assertThat(empleados).isNotNull();
        assertThat(empleados.size()).isEqualTo(2);
    }
    @DisplayName("Test para obtener una encuesta por id")
    @Test
    public void testObtenerEncuestaPorId(){
        //give
        encuesta = encuestaRepository.save(encuesta);
        //when
        Encuesta encuestaDB = encuestaRepository.findById(encuesta.getId()).get();
        //then
        assertThat(encuestaDB).isNotNull();
    }
    @DisplayName("Test para actualizar una encuesta")
    @Test
    public void actualizarEncuesta(){
        //given
        encuesta = encuestaRepository.save(encuesta);
        LocalDateTime nuevaFechaCierre = LocalDateTime.of(2023, Month.JUNE,23,11,41);
        //when
        Encuesta encuestaDB =  encuestaRepository.findById(encuesta.getId()).get();
        encuestaDB.setTitulo("titulo modificado");
        encuestaDB.setDescripcion("descripcion modificada");
        encuestaDB.setFechaFinal(nuevaFechaCierre);
        Encuesta encuestaActualizada = encuestaRepository.save(encuestaDB);
        //then
        assertThat(encuestaActualizada.getTitulo()).isEqualTo("titulo modificado");
        assertThat(encuestaActualizada.getDescripcion()).isEqualTo("descripcion modificada");
        assertThat(encuestaActualizada.getFechaFinal()).isEqualTo(nuevaFechaCierre);
    }
    @Test
    public void publicarEncuesta(){
        encuesta = encuestaRepository.save(encuesta);
        //when
        Encuesta encuestaDB =  encuestaRepository.findById(encuesta.getId()).get();
        encuestaDB.setEstado(Boolean.TRUE);
        Encuesta encuestaActualizada = encuestaRepository.save(encuestaDB);
        //then
        assertThat(encuestaActualizada.getEstado()).isEqualTo(Boolean.TRUE);
    }
    @DisplayName("Test para eliminar una encuesta")
    @Test
    public void eliminarEncuesta(){
        //given
        encuesta = encuestaRepository.save(encuesta);
        //when
        encuestaRepository.deleteById(encuesta.getId());
        Optional<Encuesta> encuestaOptional = encuestaRepository.findById(encuesta.getId());
        //then
        assertThat(encuestaOptional).isEmpty();
    }
}
