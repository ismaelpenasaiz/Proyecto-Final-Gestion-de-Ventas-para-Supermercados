package com.example.Gestion.de.Ventas.para.Supermercados.exceptions;

public class SucursalNotFoundException extends RuntimeException {
    public SucursalNotFoundException(Long id) {
        super("Sucursal no encontrada con id: " + id);
    }
}