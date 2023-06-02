package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class VentaProductoServiceIml implements VentaProductoService {

    @Autowired
    private InventarioProductoDao inventarioProductoDao;

    @Autowired
    private VentaProductoDao ventaProductoDao;

    @Override
    @Transactional(readOnly = true)
    public List<VentaProducto> obtenerListaVentaProductos() {
        return ventaProductoDao.findByEstadoVentaProductoIsTrue();
    }

    @Override
    @Transactional
    public void guardarVentaProducto(VentaProducto ventaProducto) {
        ventaProducto.setEstadoVentaProducto(true);
        ventaProductoDao.save(ventaProducto);
        List<DetalleVentaProducto> detallesVenta = ventaProducto.getDetallesVenta();
        for (DetalleVentaProducto detalleVenta : detallesVenta) {
            int cantidadVendida = detalleVenta.getCantidadProducto();
            DetalleProducto detalleProducto = detalleVenta.getDetalleProducto();
            InventarioProducto inventarioProducto = inventarioProductoDao.findByDetalleProductoAndEstadoInventarioProductoIsTrue(detalleProducto);
            if (inventarioProducto != null) {
                int cantidadActualizada = inventarioProducto.getCantidadFinalProducto() - cantidadVendida;
                inventarioProducto.setCantidadFinalProducto(cantidadActualizada);
                inventarioProductoDao.save(inventarioProducto);
            }
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
        VentaProducto ventaProductoExiste = ventaProductoDao.findById(ventaProducto.getIdVentaProducto()).orElse(null);
        if (ventaProductoExiste != null) {
            ventaProductoExiste.setEstadoVentaProducto(false);
            ventaProductoDao.save(ventaProductoExiste);
        }
    }

}
