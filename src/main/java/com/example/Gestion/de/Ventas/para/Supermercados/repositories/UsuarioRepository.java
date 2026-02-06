package com.example.Gestion.de.Ventas.para.Supermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}

