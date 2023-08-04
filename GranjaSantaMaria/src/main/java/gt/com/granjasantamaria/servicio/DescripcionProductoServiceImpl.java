package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DescripcionProductoDao;
import gt.com.granjasantamaria.modelo.DescripcionProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DescripcionProductoServiceImpl implements DescripcionProductoService {

    private final DescripcionProductoDao descripcionProductoDao;

    @Autowired
    public DescripcionProductoServiceImpl(DescripcionProductoDao descripcionProductoDao) {
        this.descripcionProductoDao = descripcionProductoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DescripcionProducto> obtenerListadoDescripcionProductos() {
        return descripcionProductoDao.findByEstadoDescripcionProductoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DescripcionProducto> obtenerListadoDescripcionProductoPaginado(Pageable pageable) {
        return descripcionProductoDao.findAllByEstadoDescripcionProductoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarDescripcionProducto(DescripcionProducto descripcionProducto) {
        descripcionProducto.setEstadoDescripcionProducto(true);
        descripcionProductoDao.save(descripcionProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public DescripcionProducto encontrarDescripcionProducto(DescripcionProducto descripcionProducto) {
        return descripcionProductoDao.findById(descripcionProducto.getIdDescripcionProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDescripcionProducto(DescripcionProducto descripcionProducto) {
        DescripcionProducto descripcionProductoExistente = descripcionProductoDao.findById(descripcionProducto.getIdDescripcionProducto()).orElse(null);
        if (descripcionProductoExistente != null) {
            descripcionProductoExistente.setEstadoDescripcionProducto(false);
            descripcionProductoDao.save(descripcionProductoExistente);
        }
    }

}
