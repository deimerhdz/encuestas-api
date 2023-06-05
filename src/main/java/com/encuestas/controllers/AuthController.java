package com.encuestas.controllers;

import com.encuestas.dto.JwtDto;
import com.encuestas.dto.LoginDto;
import com.encuestas.dto.UsuarioAuth;
import com.encuestas.entities.Usuario;
import com.encuestas.security.JwtProvider;
import com.encuestas.services.UserDetailServiceImpl;
import com.encuestas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    UsuarioService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody Usuario user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.guardar(user);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsuario(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UsuarioAuth userDetails = (UsuarioAuth) userDetailServiceImpl.loadUserByUsername(loginDto.getUsuario());
            String jwt = jwtProvider.generateToken(userDetails);
            JwtDto jwtDto =null;

            jwtDto = new JwtDto(jwt, userDetails.getNombre(), userDetails.getId());
            return ResponseEntity.ok(jwtDto);

        } catch (BadCredentialsException e) {
            return new ResponseEntity(Collections.singletonMap("message", "Usuario o contraseña invalidos."),
                    HttpStatus.FORBIDDEN);
        } catch (AuthenticationException ex) {
            return new ResponseEntity(Collections.singletonMap("message", "Usuario o contraseña invalidos."),
                    HttpStatus.FORBIDDEN);
        }
    }
}
