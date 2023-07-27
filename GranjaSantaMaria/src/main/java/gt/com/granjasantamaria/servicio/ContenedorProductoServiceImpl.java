package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.ContenedorProductoDao;
import gt.com.granjasantamaria.modelo.ContenedorProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContenedorProductoServiceImpl implements ContenedorProductoService {

    private final ContenedorProductoDao contenedorProductoDao;

    @Autowired
    public ContenedorProductoServiceImpl(ContenedorProductoDao contenedorProductoDao) {
        this.contenedorProductoDao = contenedorProductoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContenedorProducto> obtenerListadoContenedorProductos() {
        return contenedorProductoDao.findByEstadoContenedorProductoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContenedorProducto> obtenerListadoContenedorProductoPaginado(Pageable pageable) {
        return contenedorProductoDao.findAllByEstadoContenedorProductoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarContenedorProducto(ContenedorProducto contenedorProducto) {
        contenedorProducto.setEstadoContenedorProducto(true);
        contenedorProductoDao.save(contenedorProducto);
    }

    @Override
    @Transactional
    public void eliminarContenedorProducto(ContenedorProducto contenedorProducto) {
        contenedorProductoDao.delete(contenedorProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public ContenedorProducto encontrarContenedorProducto(ContenedorProducto contenedorProducto) {
        return contenedorProductoDao.findById(contenedorProducto.getIdContenedorProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaContenedorProducto(ContenedorProducto contenedorProducto) {
        ContenedorProducto contenedorProductoExistente = contenedorProductoDao.findById(contenedorProducto.getIdContenedorProducto()).orElse(null);
        if (contenedorProductoExistente != null) {
            contenedorProductoExistente.setEstadoContenedorProducto(false);
            contenedorProductoDao.save(contenedorProductoExistente);
        }
    }

}
