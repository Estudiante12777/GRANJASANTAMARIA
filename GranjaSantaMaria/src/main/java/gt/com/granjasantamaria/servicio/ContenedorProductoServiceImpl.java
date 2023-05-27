package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.ContenedorProductoDao;
import gt.com.granjasantamaria.modelo.ContenedorProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ContenedorProductoServiceImpl implements ContenedorProductoService {

    @Autowired
    private ContenedorProductoDao contenedorProductoDao;

    @Override
    public List<ContenedorProducto> obtenerListadoContenedorProductos() {
        return contenedorProductoDao.findByEstadoContenedorProductoIsTrue();
    }

    @Override
    public void guardarContenedorProducto(ContenedorProducto contenedorProducto) {
        contenedorProducto.setEstadoContenedorProducto(true);
        contenedorProductoDao.save(contenedorProducto);
    }

    @Override
    public void eliminarContenedorProducto(ContenedorProducto contenedorProducto) {
        contenedorProductoDao.delete(contenedorProducto);
    }

    @Override
    public ContenedorProducto encontrarContenedorProducto(ContenedorProducto contenedorProducto) {
        return contenedorProductoDao.findById(contenedorProducto.getIdContenedorProducto()).orElse(null);
    }

    @Override
    public void darBajaContenedorProducto(ContenedorProducto contenedorProducto) {
        ContenedorProducto contenedorProductoExistente = contenedorProductoDao.findById(contenedorProducto.getIdContenedorProducto()).orElse(null);
        if (contenedorProductoExistente != null) {
            contenedorProductoExistente.setEstadoContenedorProducto(false);
            contenedorProductoDao.save(contenedorProductoExistente);
        }
    }

}
