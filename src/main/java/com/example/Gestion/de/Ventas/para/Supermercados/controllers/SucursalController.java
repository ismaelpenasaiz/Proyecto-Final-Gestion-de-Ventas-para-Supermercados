package com.example.Gestion.de.Ventas.para.Supermercados.controllers;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.SucursalDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.services.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sucursales", description = "Operaciones CRUD para sucursales")
@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @Operation(summary = "Listar todas las sucursales")
    @GetMapping
    public List<SucursalDTO> listarActivas() {
        return sucursalService.listarActivas();
    }

    @GetMapping("/todas")
    public List<SucursalDTO> listarTodas() {
        return sucursalService.listarTodas();
    }


    @Operation(summary = "Crear una nueva sucursal")
    @PostMapping
    public ResponseEntity<SucursalDTO> crear(@Valid @RequestBody SucursalDTO dto) {
        return ResponseEntity.status(201).body(sucursalService.crear(dto));
    }

    @Operation(summary = "Actualizar una sucursal existente")
    @PutMapping("/{id}")
    public SucursalDTO actualizar(@PathVariable Long id,
                                  @RequestBody SucursalDTO dto) {
        return sucursalService.actualizar(id, dto);
    }

    @Operation(summary = "Eliminar una sucursal")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sucursalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
