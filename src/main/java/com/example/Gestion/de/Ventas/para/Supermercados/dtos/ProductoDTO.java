package com.example.Gestion.de.Ventas.para.Supermercados.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {

    private Long id;

    private String nombre;

    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    private String categoria;
}
