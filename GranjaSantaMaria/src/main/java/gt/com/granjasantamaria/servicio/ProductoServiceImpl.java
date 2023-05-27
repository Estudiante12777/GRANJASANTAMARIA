package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.ProductoDao;
import gt.com.granjasantamaria.modelo.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerListadoProductos() {
        return productoDao.findByEstadoProductoIsTrue();
    }

    @Override
    @Transactional
    public void guardarProducto(Producto producto) {
        producto.setEstadoProducto(true);
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void eliminarProducto(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto encontranProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaProducto(Producto producto) {
        Producto productoExistente = productoDao.findById(producto.getIdProducto()).orElse(null);
        if (productoExistente != null) {
            productoExistente.setEstadoProducto(false);
            productoDao.save(productoExistente);
        }
    }

}
