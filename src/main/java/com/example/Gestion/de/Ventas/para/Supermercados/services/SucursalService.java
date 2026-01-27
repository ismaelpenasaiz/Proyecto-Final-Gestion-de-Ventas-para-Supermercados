package com.example.Gestion.de.Ventas.para.Supermercados.services;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.SucursalDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Sucursal;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public List<SucursalDTO> listar() {
        return sucursalRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public SucursalDTO crear(SucursalDTO dto) {
        return toDTO(sucursalRepository.save(toEntity(dto)));
    }

    public SucursalDTO actualizar(Long id, SucursalDTO dto) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());

        return toDTO(sucursalRepository.save(sucursal));
    }

    public void eliminar(Long id) {
        sucursalRepository.deleteById(id);
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
