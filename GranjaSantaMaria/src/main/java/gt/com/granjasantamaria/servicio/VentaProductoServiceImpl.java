package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

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
    @Transactional
    public void darBajaVentaProducto(VentaProducto ventaProducto) {
        VentaProducto ventaProductoExistente = ventaProductoDao.findById(ventaProducto.getIdVentaProducto()).orElse(null);
        if (ventaProductoExistente != null) {
            ventaProductoExistente.setEstadoVentaProducto(false);
            ventaProductoDao.save(ventaProductoExistente);
        }
    }

}
