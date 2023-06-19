package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {

    @Autowired
    private VentaProductoDao ventaProductoDao;

    @Autowired
    private InventarioProductoDao inventarioProductoDao;

    @Override
    @Transactional(readOnly = true)
    public List<VentaProducto> obtenerListadoVentaProductos() {
        return ventaProductoDao.findByEstadoVentaProductoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VentaProducto> obtenerListadoVentaProductoPaginado(Pageable pageable) {
        return ventaProductoDao.findAllByEstadoVentaProductoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarVentaProducto(VentaProducto ventaProducto) {
        ventaProducto.setEstadoVentaProducto(true);
        ventaProductoDao.save(ventaProducto);
        // Obtenemos el producto correspondiente
        InventarioProducto inventarioProducto = inventarioProductoDao.findById(ventaProducto.getIdVentaProducto()).orElse(null);
        if (inventarioProducto != null) {
            // Se actualiza el inventario
            inventarioProducto.setCantidadSalidaProducto(inventarioProducto.getCantidadSalidaProducto() + ventaProducto.getCantidadProducto());
            inventarioProducto.setCantidadFinalProducto(inventarioProducto.getCantidadFinalProducto() + ventaProducto.getCantidadProducto());
            inventarioProducto.setCantidadVendidaHastaHoy(inventarioProducto.getCantidadVendidaHastaHoy() + ventaProducto.getCantidadProducto());
            inventarioProductoDao.save(inventarioProducto);
        }
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
    @Transactional
    public void darBajaVentaProducto(VentaProducto ventaProducto) {
        VentaProducto ventaProductoExistente = ventaProductoDao.findById(ventaProducto.getIdVentaProducto()).orElse(null);
        if (ventaProductoExistente != null) {
            ventaProductoExistente.setEstadoVentaProducto(false);
            ventaProductoDao.save(ventaProductoExistente);
        }
    }

}
