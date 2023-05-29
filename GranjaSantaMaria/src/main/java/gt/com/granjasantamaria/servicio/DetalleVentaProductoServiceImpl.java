package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DetalleVentaProductoDao;
import gt.com.granjasantamaria.modelo.DetalleVentaProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class DetalleVentaProductoServiceImpl implements DetalleVentaProductoService {

    @Autowired
    private DetalleVentaProductoDao detalleVentaProductoDao;

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVentaProducto> obtenerListadoDetalleVentaProductos() {
        return detalleVentaProductoDao.findByEstadoDetalleVentaProductoIsTrue();
    }

    @Override
    @Transactional
    public void guardarDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto) {
        detalleVentaProducto.setEstadoDetalleVentaProducto(true);
        detalleVentaProductoDao.save(detalleVentaProducto);
    }

    @Override
    @Transactional
    public void eliminarDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto) {
        detalleVentaProductoDao.delete(detalleVentaProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleVentaProducto encontrarDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto) {
        return detalleVentaProductoDao.findById(detalleVentaProducto.getIdDetalleVentaProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto) {
        DetalleVentaProducto detalleVentaProductoExistente = detalleVentaProductoDao.findById(detalleVentaProducto.getIdDetalleVentaProducto()).orElse(null);
        if (detalleVentaProductoExistente != null) {
            detalleVentaProductoExistente.setEstadoDetalleVentaProducto(false);
            detalleVentaProductoDao.save(detalleVentaProductoExistente);
        }
    }

}
