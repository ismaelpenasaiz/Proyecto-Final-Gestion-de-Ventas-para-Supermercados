package com.example.Gestion.de.Ventas.para.Supermercados.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SucursalDTO {

    private Long id;

    @NotBlank(message = "El nombre de la sucursal no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    private Boolean activa;

}

