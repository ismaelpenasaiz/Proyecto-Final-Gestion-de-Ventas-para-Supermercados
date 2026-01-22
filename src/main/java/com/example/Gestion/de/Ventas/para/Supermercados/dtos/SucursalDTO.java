package com.example.Gestion.de.Ventas.para.Supermercados.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SucursalDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

}

