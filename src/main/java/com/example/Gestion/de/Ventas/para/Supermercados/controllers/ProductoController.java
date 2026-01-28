package com.example.Gestion.de.Ventas.para.Supermercados.controllers;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.ProductoDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productos", description = "Operaciones CRUD para productos")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public List<ProductoDTO> listar() {
        return productoService.listar();
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.crear(dto));
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public ProductoDTO actualizar(@PathVariable Long id,
                                  @Valid @RequestBody ProductoDTO dto) {
        return productoService.actualizar(id, dto);
    }

    @Operation(summary = "Eliminar un producto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
