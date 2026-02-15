package com.example.Gestion.de.Ventas.para.Supermercados.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;

    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotNull
    @Positive(message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    private String categoria;
}
