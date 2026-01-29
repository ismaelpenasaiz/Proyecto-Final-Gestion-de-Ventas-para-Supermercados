package com.example.Gestion.de.Ventas.para.Supermercados.repositories;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Producto;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    @Query("SELECT s FROM Sucursal s WHERE s.activo = true")
    List<Sucursal> findActivas();

    // Todos los productos, activos o no
    @Query("SELECT s FROM Sucursal s")
    List<Sucursal> findTodos();
}
