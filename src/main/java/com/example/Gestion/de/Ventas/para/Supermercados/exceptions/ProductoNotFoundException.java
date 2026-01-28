package com.example.Gestion.de.Ventas.para.Supermercados.exceptions;

public class    ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(Long id) {
        super("Producto no encontrado con id: " + id);
    }
}