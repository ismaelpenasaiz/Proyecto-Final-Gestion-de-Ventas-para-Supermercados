package com.example.Gestion.de.Ventas.para.Supermercados.repositories;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findBySucursalIdAndActivaTrue(Long sucursalId);

    List<Venta> findByFechaBetweenAndActivaTrue(LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT DISTINCT v FROM Venta v LEFT JOIN FETCH v.detalles WHERE v.activa = true")
    List<Venta> findVentasActivasConDetalles();

}
