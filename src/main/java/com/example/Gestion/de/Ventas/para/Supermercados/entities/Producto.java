package com.example.Gestion.de.Ventas.para.Supermercados.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.*;


@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal precio;

    private String categoria;

    private boolean activo = true;

    @OneToMany(mappedBy = "producto")
    private List<VentaDetalle> detalles = new ArrayList<>();
}
