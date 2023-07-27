package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {

    private final VentaProductoDao ventaProductoDao;

    private final InventarioProductoDao inventarioProductoDao;

    @Autowired
    public VentaProductoServiceImpl(VentaProductoDao ventaProductoDao, InventarioProductoDao inventarioProductoDao) {
        this.ventaProductoDao = ventaProductoDao;
        this.inventarioProductoDao = inventarioProductoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaProducto> obtenerListadoVentaProducto() {
        LocalDate fechaActual = LocalDate.now();
        return ventaProductoDao.findByFechaVentaProductoAndEstadoVentaProductoIsTrue(fechaActual);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaProducto> obtenerListaTotalVentaProducto() {
        LocalDate fechaActual = LocalDate.now();
        return ventaProductoDao.findByFechaVentaProductoAndEstadoVentaProductoIsTrue(fechaActual);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VentaProducto> obtenerListaTotalVentaProductoPaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable) {
        return ventaProductoDao.findByFechaVentaProductoBetween(fechaInicio, fechaFin, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VentaProducto> obtenerListaTotalVentaProductoPaginadoPorFechaAndIdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto, Pageable pageable) {
        return ventaProductoDao.findByFechaVentaProductoAndDetalleProducto(fechaInicio, fechaFin, idDetalleProducto, pageable);
    }

    @Override
    @Transactional
    public void guardarVentaProducto(VentaProducto ventaProducto) {
        ventaProducto.setEstadoVentaProducto(true);
        InventarioProducto inventarioProducto = inventarioProductoDao.findById(ventaProducto.getInventarioProducto().getIdInventarioProducto()).orElse(null);
        if (inventarioProducto != null) {
            // Calcula los valores actualizados
            int cantidadVendida = inventarioProducto.getCantidadVendidaHastaHoy() + ventaProducto.getCantidadProducto();
            int cantidadFinal = inventarioProducto.getCantidadIngresadaProducto() - cantidadVendida;
            // Actualiza el inventario
            inventarioProducto.setCantidadSalidaProducto(inventarioProducto.getCantidadSalidaProducto() + ventaProducto.getCantidadProducto());
            inventarioProducto.setCantidadVendidaHastaHoy(cantidadVendida);
            inventarioProducto.setCantidadFinalProducto(cantidadFinal);
            inventarioProductoDao.save(inventarioProducto);
        }
        ventaProductoDao.save(ventaProducto);
    }

    @Override
    @Transactional
    public void eliminarVentaProducto(VentaProducto ventaProducto) {
        ventaProductoDao.delete(ventaProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public VentaProducto encontrarVentaProducto(VentaProducto ventaProducto) {
        return ventaProductoDao.findById(ventaProducto.getIdVentaProducto()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaProducto> encontrarTotalVentaProducto(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventaProductoDao.findByFechaVentaProductoBetween(fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaProducto> encontrarTotalVentaProductoAndIdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto) {
        return ventaProductoDao.findByFechaVentaProductoAndDetalleProducto(fechaInicio, fechaFin, idDetalleProducto);
    }

    @Override
    @Transactional
    public void darBajaVentaProducto(VentaProducto ventaProducto) {
        VentaProducto ventaProductoExistente = ventaProductoDao.findById(ventaProducto.getIdVentaProducto()).orElse(null);
        if (ventaProductoExistente != null) {
            ventaProductoExistente.setEstadoVentaProducto(false);
            ventaProductoDao.save(ventaProductoExistente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Double obtenerTotalVentas() {
        return ventaProductoDao.obtenerTotalVentas();
    }

}
