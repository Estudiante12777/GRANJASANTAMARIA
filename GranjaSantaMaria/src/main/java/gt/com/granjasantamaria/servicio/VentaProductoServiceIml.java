package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.VentaProductoDao;
import gt.com.granjasantamaria.modelo.VentaProducto;
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
