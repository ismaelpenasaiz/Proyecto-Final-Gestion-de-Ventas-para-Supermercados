package com.example.Gestion.de.Ventas.para.Supermercados.services;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.VentaDetalle;
import com.example.Gestion.de.Ventas.para.Supermercados.dtos.VentaDTO.ProductoMasVendidoDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Servicio encargado de generar estadísticas relacionadas con las ventas.
 */
@Service
@RequiredArgsConstructor
public class EstadisticasService {

    //Repositorio de ventas para obtener las ventas activas y sus detalles
    private final VentaRepository ventaRepository;

    //Obtiene el producto más vendido de las ventas activas
    public ProductoMasVendidoDTO obtenerProductoMasVendido() {

        return ventaRepository.findVentasActivasConDetalles()
                .stream()
                // Convierte el stream de Venta en un stream de VentaDetalle
                .flatMap(venta -> venta.getDetalles().stream())
                // Agrupa los detalles por producto y suma la cantidad vendida
                .collect(Collectors.groupingBy(
                        VentaDetalle::getProducto,
                        Collectors.summingLong(VentaDetalle::getCantidad)
                ))
                // Convierte el Map en stream para buscar el producto con mayor cantidad vendida
                .entrySet()
                .stream()
                // Obtiene la entrada con el valor más alto (más vendido)
                .max(Map.Entry.comparingByValue())
                // Mapea el resultado al DTO de respuesta
                .map(entry -> new ProductoMasVendidoDTO(
                        entry.getKey().getId(),
                        entry.getKey().getNombre(),
                        entry.getValue()
                ))
                // Lanza excepción si no hay ventas
                .orElseThrow(() ->
                        new IllegalStateException("No hay ventas registradas"));
    }
}
