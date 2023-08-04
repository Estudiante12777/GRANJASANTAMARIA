package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.InventarioProductoDao;
import gt.com.granjasantamaria.modelo.InventarioProducto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventarioProductoServiceImpl implements InventarioProductoService {

    private final InventarioProductoDao inventarioProductoDao;

    @Autowired
    public InventarioProductoServiceImpl(InventarioProductoDao inventarioProductoDao) {
        this.inventarioProductoDao = inventarioProductoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioProducto> obtenerListadoInventarioProductos() {
        return inventarioProductoDao.findByEstadoInventarioProductoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InventarioProducto> obtenerListadoInventarioProductoPaginado(Pageable pageable) {
        return inventarioProductoDao.findAllByEstadoInventarioProductoIsTrue(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InventarioProducto> obtenerListaTotalInventarioProductoPaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable) {
        return inventarioProductoDao.findByFechaInventarioProductoBetween(fechaInicio, fechaFin, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InventarioProducto> obtenerListaTotalInventarioProductoPaginadoPorFechaAndIdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto, Pageable pageable) {
        return inventarioProductoDao.findByFechaInventarioProductoBetweenAndDetalleProducto_IdDetalleProducto(fechaInicio, fechaFin, idDetalleProducto, pageable);
    }

    @Override
    @Transactional
    public void guardarInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProducto.setEstadoInventarioProducto(true);
        inventarioProductoDao.save(inventarioProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioProducto encontrarInventarioProducto(InventarioProducto inventarioProducto) {
        return inventarioProductoDao.findById(inventarioProducto.getIdInventarioProducto()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public int obtenerExistenciaProducto(Long idInventarioProducto) {
        InventarioProducto inventario = inventarioProductoDao.findById(idInventarioProducto).orElse(null);
        if (inventario != null) {
            return inventario.getCantidadIngresadaProducto() - inventario.getCantidadVendidaHastaHoy();
        } else {
            return 0;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioProducto> encontrarTotalInventarioProducto(LocalDate fechaInicio, LocalDate fechaFin) {
        return inventarioProductoDao.findByFechaInventarioProductoBetween(fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioProducto> encontrarTotalInventarioProductoAndIdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto) {
        return inventarioProductoDao.findByFechaInventarioProductoBetweenAndDetalleProducto_IdDetalleProducto(fechaInicio, fechaFin, idDetalleProducto);
    }

    @Override
    @Transactional
    public void darBajaInventarioProducto(InventarioProducto inventarioProducto) {
        InventarioProducto inventarioProductoExistente = inventarioProductoDao.findById(inventarioProducto.getIdInventarioProducto()).orElse(null);
        if (inventarioProductoExistente != null) {
            inventarioProductoExistente.setEstadoInventarioProducto(false);
            inventarioProductoDao.save(inventarioProductoExistente);
        }
    }

}
