package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DetalleProductoDao;
import gt.com.granjasantamaria.modelo.DetalleProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleProductoServiceImpl implements DetalleProductoService {

    @Autowired
    private DetalleProductoDao detalleProductoDao;

    @Override
    public List<DetalleProducto> obtenerListadoDetalleProductos() {
        return detalleProductoDao.findByEstadoDetalleProductoIsTrue();
    }

    @Override
    public void guardarDetalleProducto(DetalleProducto detalleProducto) {
        detalleProducto.setEstadoDetalleProducto(true);
        detalleProductoDao.save(detalleProducto);
    }

    @Override
    public void eliminarDetalleProducto(DetalleProducto detalleProducto) {
        detalleProductoDao.delete(detalleProducto);
    }

    @Override
    public DetalleProducto encontrarDetalleProducto(DetalleProducto detalleProducto) {
        return detalleProductoDao.findById(detalleProducto.getIdDetalleProducto()).orElse(null);
    }

    @Override
    public void darBajaDetalleProducto(DetalleProducto detalleProducto) {
        DetalleProducto detalleProductoExistente = detalleProductoDao.findById(detalleProducto.getIdDetalleProducto()).orElse(null);
        if (detalleProductoExistente != null) {
            detalleProductoExistente.setEstadoDetalleProducto(false);
            detalleProductoDao.save(detalleProductoExistente);
        }
    }

}
