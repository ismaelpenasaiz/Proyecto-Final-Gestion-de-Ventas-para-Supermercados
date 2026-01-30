package com.example.Gestion.de.Ventas.para.Supermercados.services;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.SucursalDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Sucursal;
import com.example.Gestion.de.Ventas.para.Supermercados.exceptions.SucursalNotFoundException;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public List<SucursalDTO> listarActivas() {
        return sucursalRepository.findActivas()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<SucursalDTO> listarTodas() {
        return sucursalRepository.findTodos()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public SucursalDTO crear(SucursalDTO dto) {
        Sucursal sucursal = toEntity(dto);
        sucursal.setActiva(true);
        return toDTO(sucursalRepository.save(sucursal));
    }

    public SucursalDTO actualizar(Long id, SucursalDTO dto) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException(id));

        if (dto.getNombre() != null) {
            sucursal.setNombre(dto.getNombre());
        }

        if (dto.getDireccion() != null) {
            sucursal.setDireccion(dto.getDireccion());
        }

        if (dto.getActiva() != null) {
            sucursal.setActiva(dto.getActiva());
        }

        return toDTO(sucursalRepository.save(sucursal));
    }

    @Transactional
    public void eliminar(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException(id));

        // Marcamos la sucursal como inactiva
        sucursal.setActiva(false);

        // Marcamos todas sus ventas como inactivas
        sucursal.getVentas().forEach(venta -> venta.setActiva(false));

        sucursalRepository.save(sucursal);
    }




    private SucursalDTO toDTO(Sucursal sucursal) {
        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        return dto;
    }

    private Sucursal toEntity(SucursalDTO dto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(dto.getId());
        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());
        return sucursal;
    }
}
