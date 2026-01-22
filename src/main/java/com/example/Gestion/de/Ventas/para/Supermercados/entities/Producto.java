package com.example.Gestion.de.Ventas.para.Supermercados.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

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
}
