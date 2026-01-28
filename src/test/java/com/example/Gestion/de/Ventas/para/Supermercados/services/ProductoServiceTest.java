package com.example.Gestion.de.Ventas.para.Supermercados.services;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.ProductoDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Producto;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private ProductoDTO productoDTO;
    private Producto producto;

    @BeforeEach
    void setUp() {
        productoDTO = new ProductoDTO();
        productoDTO.setNombre("Arroz");
        productoDTO.setPrecio(new BigDecimal("2.50"));
        productoDTO.setCategoria("Alimentos");

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Arroz");
        producto.setPrecio(new BigDecimal("2.50"));
        producto.setCategoria("Alimentos");
    }

    @Test
    void crearProducto_exitoso() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ProductoDTO resultado = productoService.crear(productoDTO);

        assertNotNull(resultado);
        assertEquals("Arroz", resultado.getNombre());
        assertEquals(new BigDecimal("2.50"), resultado.getPrecio());
        assertEquals("Alimentos", resultado.getCategoria());

        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void actualizarProducto_exitoso() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ProductoDTO actualizado = new ProductoDTO();
        actualizado.setNombre("Arroz Integral");
        actualizado.setPrecio(new BigDecimal("3.00"));
        actualizado.setCategoria("Alimentos");

        ProductoDTO resultado = productoService.actualizar(1L, actualizado);

        assertNotNull(resultado);
        verify(productoRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void actualizarProducto_noEncontrado() {
        when(productoRepository.findById(999L)).thenReturn(Optional.empty());

        ProductoDTO actualizado = new ProductoDTO();
        actualizado.setNombre("Producto");
        actualizado.setPrecio(new BigDecimal("10.00"));

        assertThrows(RuntimeException.class, () -> {
            productoService.actualizar(999L, actualizado);
        });

        verify(productoRepository, times(1)).findById(999L);
        verify(productoRepository, never()).save(any(Producto.class));
    }
}