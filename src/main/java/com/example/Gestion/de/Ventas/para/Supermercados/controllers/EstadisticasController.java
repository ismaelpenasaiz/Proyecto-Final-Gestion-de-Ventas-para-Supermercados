package com.example.Gestion.de.Ventas.para.Supermercados.controllers;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.VentaDTO.ProductoMasVendidoDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.services.EstadisticasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    private final EstadisticasService estadisticaService;

    public EstadisticasController(EstadisticasService estadisticaService) {
        this.estadisticaService = estadisticaService;
    }

    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<ProductoMasVendidoDTO> obtenerProductoMasVendido() {
        return ResponseEntity.ok(estadisticaService.obtenerProductoMasVendido());
    }
}
