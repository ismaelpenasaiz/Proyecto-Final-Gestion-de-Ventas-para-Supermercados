package com.example.Gestion.de.Ventas.para.Supermercados.controllers;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.VentaDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.services.VentaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaDTO.VentaResponseDTO> crear(@Valid @RequestBody VentaDTO.VentaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ventaService.registrarVenta(dto));
    }

    @GetMapping
    public List<VentaDTO.VentaResponseDTO> listar(
            @RequestParam(required = false) Long sucursalId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        return ventaService.listar(sucursalId, fecha);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarLogico(@PathVariable Long id) {
        ventaService.borrarLogico(id);
        return ResponseEntity.noContent().build();
    }
}