package com.example.Gestion.de.Ventas.para.Supermercados.controllers;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.SucursalDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.services.SucursalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping
    public List<SucursalDTO> listar() {
        return sucursalService.listar();
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crear(@Valid @RequestBody SucursalDTO dto) {
        return ResponseEntity.status(201).body(sucursalService.crear(dto));
    }

    @PutMapping("/{id}")
    public SucursalDTO actualizar(@PathVariable Long id,
                                  @Valid @RequestBody SucursalDTO dto) {
        return sucursalService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sucursalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

