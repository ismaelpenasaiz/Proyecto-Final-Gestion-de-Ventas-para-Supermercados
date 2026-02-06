package com.example.Gestion.de.Ventas.para.Supermercados.security;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import java.util.List;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Usuario;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repo;

    public CustomUserDetailsService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario user = repo.findByUsername(username)
                .orElseThrow();

        return new User(user.getUsername(), user.getPassword(), List.of());
    }
}
