package com.example.Gestion.de.Ventas.para.Supermercados.services;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.ProductoDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Producto;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> listar() {
        return productoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProductoDTO crear(ProductoDTO dto) {
        Producto producto = toEntity(dto);
        return toDTO(productoRepository.save(producto));
    }

    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria());

        return toDTO(productoRepository.save(producto));
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }


    private ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }

    private Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }
}
