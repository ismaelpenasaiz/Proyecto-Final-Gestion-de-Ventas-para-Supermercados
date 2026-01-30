package com.example.Gestion.de.Ventas.para.Supermercados.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SucursalDTO {

    private Long id;

    private String nombre;

    private String direccion;

    private Boolean activa;

}

