package com.example.Gestion.de.Ventas.para.Supermercados.repositories;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import org.springframework.data.repository.query.Param;



import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findBySucursalIdAndActivaTrue(Long sucursalId);

    List<Venta> findByFechaBetweenAndActivaTrue(LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT DISTINCT v FROM Venta v LEFT JOIN FETCH v.detalles WHERE v.activa = true")
    List<Venta> findVentasActivasConDetalles();

    @Query("""
        SELECT v FROM Venta v
        LEFT JOIN FETCH v.detalles d
        LEFT JOIN FETCH d.producto
        WHERE (:sucursalId IS NULL OR v.sucursal.id = :sucursalId)
          AND (:inicio IS NULL OR v.fecha >= :inicio)
          AND (:fin IS NULL OR v.fecha <= :fin)
          AND (:soloActivas IS NULL OR v.activa = :soloActivas)
    """)
    List<Venta> findVentasFiltradas(
            @Param("sucursalId") Long sucursalId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin,
            @Param("soloActivas") Boolean soloActivas
    );
}
