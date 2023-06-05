package com.encuestas.security;

import com.encuestas.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailServiceImpl UserDetailServiceImpl;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
            throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token != null && token.contains("Bearer")) {
                token = token.replace("Bearer", "");

                if (jwtProvider.validateToken(token)) {

                    String email = jwtProvider.extractUsername(token);
                    UserDetails userDatail = UserDetailServiceImpl.loadUserByUsername(email);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDatail, null,
                            userDatail.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }


        } catch (Exception e) {

        }
        filter.doFilter(request, response);

    }

}
