package com.example.Gestion.de.Ventas.para.Supermercados.exceptions;

public class VentaNotFoundException extends RuntimeException {
    public VentaNotFoundException(Long id) {
        super("Venta no encontrada con id: " + id);
    }
}
