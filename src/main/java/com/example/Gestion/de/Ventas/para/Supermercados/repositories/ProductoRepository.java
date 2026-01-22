package com.example.Gestion.de.Ventas.para.Supermercados.repositories;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

