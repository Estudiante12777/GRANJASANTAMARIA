package gt.com.granjasantamaria.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import gt.com.granjasantamaria.dao.MedidaProductoDao;
import gt.com.granjasantamaria.modelo.MedidaProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedidaProductoServiceImpl implements MedidaProductoService {

    private final MedidaProductoDao medidaProductoDao;

    @Autowired
    public MedidaProductoServiceImpl(MedidaProductoDao medidaProductoDao) {
        this.medidaProductoDao = medidaProductoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedidaProducto> obtenerListadoMedidaProductos() {
        return medidaProductoDao.findByEstadoMedidaProductoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MedidaProducto> obtenerListadoMedidaProductoPaginado(Pageable pageable) {
        return medidaProductoDao.findAllByEstadoMedidaProductoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarMedidaProducto(MedidaProducto medidaProducto) {
        medidaProducto.setEstadoMedidaProducto(true);
        medidaProductoDao.save(medidaProducto);
    }

    @Override
    @Transactional
    public void eliminarMedidaProducto(MedidaProducto medidaProducto) {
        medidaProductoDao.delete(medidaProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public MedidaProducto encontrarMedidaProducto(MedidaProducto medidaProducto) {
        return medidaProductoDao.findById(medidaProducto.getIdMedidaProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaMedidaProducto(MedidaProducto medidaProducto) {
        MedidaProducto medidaProductoExistente = medidaProductoDao.findById(medidaProducto.getIdMedidaProducto()).orElse(null);
        if (medidaProductoExistente != null) {
            medidaProductoExistente.setEstadoMedidaProducto(false);
            medidaProductoDao.save(medidaProductoExistente);
        }
    }

}
