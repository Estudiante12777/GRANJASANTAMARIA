package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DetalleProductoDao;
import gt.com.granjasantamaria.modelo.DetalleProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleProductoServiceImpl implements DetalleProductoService {

    private final DetalleProductoDao detalleProductoDao;

    @Autowired
    public DetalleProductoServiceImpl(DetalleProductoDao detalleProductoDao) {
        this.detalleProductoDao = detalleProductoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleProducto> obtenerListadoDetalleProductos() {
        return detalleProductoDao.findByEstadoDetalleProductoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetalleProducto> obtenerListadoDetalleProductoPaginado(Pageable pageable) {
        return detalleProductoDao.findAllByEstadoDetalleProductoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarDetalleProducto(DetalleProducto detalleProducto) {
        detalleProducto.setEstadoDetalleProducto(true);
        detalleProductoDao.save(detalleProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleProducto encontrarDetalleProducto(DetalleProducto detalleProducto) {
        return detalleProductoDao.findById(detalleProducto.getIdDetalleProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDetalleProducto(DetalleProducto detalleProducto) {
        DetalleProducto detalleProductoExistente = detalleProductoDao.findById(detalleProducto.getIdDetalleProducto()).orElse(null);
        if (detalleProductoExistente != null) {
            detalleProductoExistente.setEstadoDetalleProducto(false);
            detalleProductoDao.save(detalleProductoExistente);
        }
    }

}
