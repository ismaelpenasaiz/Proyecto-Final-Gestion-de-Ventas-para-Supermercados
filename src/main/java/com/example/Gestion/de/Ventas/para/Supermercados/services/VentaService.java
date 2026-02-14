package com.example.Gestion.de.Ventas.para.Supermercados.services;

import com.example.Gestion.de.Ventas.para.Supermercados.dtos.VentaDTO;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Producto;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Sucursal;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.Venta;
import com.example.Gestion.de.Ventas.para.Supermercados.entities.VentaDetalle;
import com.example.Gestion.de.Ventas.para.Supermercados.exceptions.ProductoNotFoundException;
import com.example.Gestion.de.Ventas.para.Supermercados.exceptions.SucursalNotFoundException;
import com.example.Gestion.de.Ventas.para.Supermercados.exceptions.VentaNotFoundException;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.ProductoRepository;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.SucursalRepository;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public VentaService(VentaRepository ventaRepository,
                        ProductoRepository productoRepository,
                        SucursalRepository sucursalRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.sucursalRepository = sucursalRepository;
    }

    public VentaDTO.VentaResponseDTO registrarVenta(VentaDTO.VentaRequestDTO request) {

        // Buscar sucursal
        Sucursal sucursal = sucursalRepository.findById(request.getSucursalId())
                .orElseThrow(() -> new SucursalNotFoundException(request.getSucursalId()));

        if (!Boolean.TRUE.equals(sucursal.getActiva())) {
            throw new IllegalStateException(
                    "No se puede registrar la venta: la sucursal está inactiva");
        }
        // crea una nueva venta como objeto
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setSucursal(sucursal);
        venta.setActiva(true);

        // Crear detalles de venta
        List<VentaDetalle> detalles = request.getItems().stream().map(item -> {

            // Buscar producto activo
            Producto producto = productoRepository.findActivoById(item.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "El producto con ID " + item.getProductoId() + " no existe o no está activo."));

            VentaDetalle detalle = new VentaDetalle();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());

            return detalle;

        }).toList();

        venta.setDetalles(detalles);

        // Calcular total
        BigDecimal total = detalles.stream()
                .map(d -> d.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venta.setTotal(total);
        // Se guarda la venta también los detalles gracias a la relación @OneToMany
        // se convierte en DTO
        return toResponseDTO(ventaRepository.save(venta));
    }


        // permite filtro por sucursal, fecha, y ventas activas
    public List<VentaDTO.VentaResponseDTO> listar(Long sucursalId, LocalDate fecha, Boolean soloActivas) {


        LocalDateTime inicio = null;
        LocalDateTime fin = null;

        if (fecha != null) {
            inicio = fecha.atStartOfDay();
            fin = fecha.atTime(23, 59, 59);
        }

        List<Venta> ventas = ventaRepository.findVentasFiltradas(
                sucursalId,
                inicio,
                fin,
                soloActivas
        );

        return ventas.stream()
                .map(this::toResponseDTO)
                .toList();


    }


    public void borrarLogico(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new VentaNotFoundException(id));

        venta.setActiva(false);
    }



    private VentaDTO.VentaResponseDTO toResponseDTO(Venta venta) {
        VentaDTO.VentaResponseDTO dto = new VentaDTO.VentaResponseDTO();
        dto.setId(venta.getId());
        dto.setFecha(venta.getFecha());
        dto.setSucursal(venta.getSucursal().getNombre());
        dto.setTotal(venta.getTotal());

        dto.setDetalles(
                venta.getDetalles().stream().map(det -> {
                    VentaDTO.VentaDetalleResponseDTO d = new VentaDTO.VentaDetalleResponseDTO();
                    d.setProducto(det.getProducto().getNombre());
                    d.setCantidad(det.getCantidad());
                    d.setPrecioUnitario(det.getPrecioUnitario());
                    return d;
                }).toList()
        );

        return dto;
    }
}
