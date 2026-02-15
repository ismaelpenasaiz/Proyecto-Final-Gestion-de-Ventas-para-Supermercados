package com.example.Gestion.de.Ventas.para.Supermercados.services;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.ProductoDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Producto;
import com.example.Gestion.de.Ventas.para.Supermercados.exceptions.ProductoNotFoundException;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Listado solo activos
    public List<ProductoDTO> listarActivos() {
        return productoRepository.findActivos()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // Listado de todos
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findTodos()
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
                .orElseThrow(() -> new ProductoNotFoundException(id));

        if (dto.getNombre() != null) {
            producto.setNombre(dto.getNombre());
        }

        if (dto.getPrecio() != null) {
            producto.setPrecio(dto.getPrecio());
        }

        if (dto.getCategoria() != null) {
            producto.setCategoria(dto.getCategoria());
        }

        if (dto.getStock() != null) {
            producto.setStock(dto.getStock());
        }

        if (dto.getActivo() != null) {
            producto.setActivo(dto.getActivo());
        }

        return toDTO(productoRepository.save(producto));
    }

    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        if (!producto.getDetalles().isEmpty()) {
            // Tiene ventas, solo borrado lógico
            producto.setActivo(false);
            productoRepository.save(producto);
        } else {
            // Nunca vendido, se puede borrar físicamente
            productoRepository.delete(producto);
        }
    }



    private ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setCategoria(producto.getCategoria());
        dto.setStock(producto.getStock());
        dto.setActivo(producto.isActivo());
        return dto;
    }

    private Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria());
        producto.setStock(dto.getStock());
        return producto;
    }


}

