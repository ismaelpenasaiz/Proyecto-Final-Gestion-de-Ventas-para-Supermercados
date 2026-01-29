package com.example.Gestion.de.Ventas.para.Supermercados.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VentaDTO {

    @Data
    public static class ItemVentaDTO {
        @NotNull
        private Long productoId;

        @Positive
        private Integer cantidad;
    }

    @Data
    public static class VentaRequestDTO {
        @NotNull
        private Long sucursalId;

        @NotEmpty
        private List<ItemVentaDTO> items;

        public static class ProductoCantidadDTO {
            private Long productoId;
            private Long cantidad;
        }
    }

    @Data
    public static class VentaResponseDTO {
        private Long id;
        private LocalDateTime fecha;
        private String sucursal;
        private BigDecimal total;
        private List<VentaDetalleResponseDTO> detalles;
    }

    @Data
    public static class VentaDetalleResponseDTO {
        private String producto;
        private Integer cantidad;
        private BigDecimal precioUnitario;
    }

    @Data
    @AllArgsConstructor
    public static class ProductoMasVendidoDTO{
        private Long productoID;
        private String nombre;
        private Long totalVendido;

    }
}