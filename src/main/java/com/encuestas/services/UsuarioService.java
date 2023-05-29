package com.encuestas.services;

import com.encuestas.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {

     Usuario guardar(Usuario usuario);

     Optional<Usuario> getByusuario(String usuario);


     boolean existsByUsuario(String usuario);



}
