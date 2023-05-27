package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DescripcionProductoDao;
import gt.com.granjasantamaria.modelo.DescripcionProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class DescripcionProductoServiceImpl implements DescripcionProductoService {

    @Autowired
    private DescripcionProductoDao descripcionProductoDAO;

    @Override
    public List<DescripcionProducto> obtenerListadoDescripcionProductos() {
        return descripcionProductoDAO.findByEstadoDescripcionProductoIsTrue();
    }

    @Override
    public void guardarDescripcionProducto(DescripcionProducto descripcionProducto) {
        descripcionProducto.setEstadoDescripcionProducto(true);
        descripcionProductoDAO.save(descripcionProducto);
    }

    @Override
    public void eliminarDescripcionProducto(DescripcionProducto descripcionProducto) {
        descripcionProductoDAO.delete(descripcionProducto);
    }

    @Override
    public DescripcionProducto encontrarDescripcionProducto(DescripcionProducto descripcionProducto) {
        return descripcionProductoDAO.findById(descripcionProducto.getIdDescripcionProducto()).orElse(null);
    }

    @Override
    public void darBajaDescripcionProducto(DescripcionProducto descripcionProducto) {
        DescripcionProducto descripcionProductoExistente = descripcionProductoDAO.findById(descripcionProducto.getIdDescripcionProducto()).orElse(null);
        if (descripcionProductoExistente != null) {
            descripcionProductoExistente.setEstadoDescripcionProducto(false);
            descripcionProductoDAO.save(descripcionProductoExistente);
        }
    }

}
