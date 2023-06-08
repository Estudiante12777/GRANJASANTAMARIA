package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.InventarioProductoDao;
import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.InventarioProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class InventarioProductoServiceImpl implements InventarioProductoService {

    @Autowired
    private InventarioProductoDao inventarioProductoDao;

    @Override
    @Transactional(readOnly = true)
    public List<InventarioProducto> obtenerListadoInventarioProductos() {
        return inventarioProductoDao.findByEstadoInventarioProductoIsTrue();
    }

    @Override
    @Transactional
    public void guardarInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProducto.setEstadoInventarioProducto(true);
        inventarioProductoDao.save(inventarioProducto);
    }

    @Override
    @Transactional
    public void eliminarInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProductoDao.delete(inventarioProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioProducto encontrarInventarioProducto(InventarioProducto inventarioProducto) {
        return inventarioProductoDao.findById(inventarioProducto.getIdInventarioProducto()).orElse(null);
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

    // En la clase InventarioProductoServiceImpl
    @Override
    @Transactional(readOnly = true)
    public InventarioProducto obtenerInventarioProductoPorProducto(DetalleProducto detalleProducto) {
        return inventarioProductoDao.findByDetalleProductoAndEstadoInventarioProductoIsTrue(detalleProducto);
    }

}