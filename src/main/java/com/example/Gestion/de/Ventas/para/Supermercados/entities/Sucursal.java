package com.example.Gestion.de.Ventas.para.Supermercados.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sucursales")
@Data
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    private boolean activa = true;

    @OneToMany(mappedBy = "sucursal")
    private List<Venta> ventas = new ArrayList<>();
}

