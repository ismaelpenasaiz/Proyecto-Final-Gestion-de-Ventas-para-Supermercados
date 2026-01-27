package com.example.Gestion.de.Ventas.para.Supermercados.tests;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.VentaDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.services.VentaService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class VentaIntegrationTest {

    @Autowired
    private VentaService ventaService;

    @Test
    void registrarVenta_ok() {
        VentaDTO.VentaRequestDTO dto = new VentaDTO.VentaRequestDTO();
        dto.setSucursalId(1L);

        VentaDTO.ItemVentaDTO item = new VentaDTO.ItemVentaDTO();
        item.setProductoId(1L);
        item.setCantidad(2);

        dto.setItems(List.of(item));

        VentaDTO.VentaResponseDTO response = ventaService.registrarVenta(dto);

        assertNotNull(response.getId());
        assertTrue(response.getTotal().compareTo(BigDecimal.ZERO) > 0);
    }
}