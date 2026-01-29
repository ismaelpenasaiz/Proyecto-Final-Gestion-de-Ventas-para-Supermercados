package com.example.Gestion.de.Ventas.para.Supermercados.repositories;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Solo productos activos
    @Query("SELECT p FROM Producto p WHERE p.activo = true")
    List<Producto> findActivos();

    // Todos los productos, activos o no
    @Query("SELECT p FROM Producto p")
    List<Producto> findTodos();

    @Query("SELECT p FROM Producto p WHERE p.id = :id AND p.activo = true")
    Optional<Producto> findActivoById(Long id);
}

