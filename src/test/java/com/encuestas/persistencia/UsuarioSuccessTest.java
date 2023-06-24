package com.encuestas.persistencia;

import com.encuestas.entities.*;
import com.encuestas.repositories.UsuarioRepository;
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
public class UsuarioSuccessTest {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    @BeforeEach
    void setup(){
        usuario = new Usuario(1l,"Deimer Hernandez","Devo1","12123","21773", LocalDateTime.now(),true);
    }
    @DisplayName("Test para guardar un usuario")
    @Test
    public void TestguardarUsuario(){
        //given
        Usuario usuario = new Usuario(1l,"Deimer Hernandez","Devo1","12123","21773", LocalDateTime.now(),true);
        //when
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        //then
        assertThat(usuarioGuardado).isNotNull();
        assertThat(usuarioGuardado.getId()).isGreaterThan(0L);
        assertThat(usuarioGuardado.getPassword()).isEqualTo("12123");
    }
    @Test
    public void testObtenerUsuario(){
        //give
        usuario = usuarioRepository.save(usuario);
        //when
        Usuario usuarioDB = usuarioRepository.findById(usuario.getId()).get();
        //then
        assertThat(usuarioDB).isNotNull();
    }

}
