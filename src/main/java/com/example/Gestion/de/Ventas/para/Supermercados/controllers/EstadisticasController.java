package com.example.Gestion.de.Ventas.para.Supermercados.controllers;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.VentaDTO.ProductoMasVendidoDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.services.EstadisticasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Estadísticas", description = "Consultas y reportes estadísticos")
@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    private final EstadisticasService estadisticaService;

    public EstadisticasController(EstadisticasService estadisticaService) {
        this.estadisticaService = estadisticaService;
    }

    @Operation(summary = "Obtener el producto más vendido")
    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<ProductoMasVendidoDTO> obtenerProductoMasVendido() {
        return ResponseEntity.ok(estadisticaService.obtenerProductoMasVendido());
    }
}
