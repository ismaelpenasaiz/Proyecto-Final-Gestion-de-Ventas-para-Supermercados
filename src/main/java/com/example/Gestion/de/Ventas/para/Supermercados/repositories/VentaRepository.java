package com.example.Gestion.de.Ventas.para.Supermercados.repositories;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findBySucursalIdAndActivaTrue(Long sucursalId);

    List<Venta> findByFechaBetweenAndActivaTrue(LocalDateTime inicio, LocalDateTime fin);

    List<Venta> findVentasActivasConDetalles();
}