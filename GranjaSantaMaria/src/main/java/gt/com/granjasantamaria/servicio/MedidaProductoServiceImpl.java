package gt.com.granjasantamaria.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import gt.com.granjasantamaria.dao.MedidaProductoDao;
import gt.com.granjasantamaria.modelo.MedidaProducto;
import org.springframework.stereotype.Service;

@Service
public class MedidaProductoServiceImpl implements MedidaProductoService {

    @Autowired
    private MedidaProductoDao medidaProductoDao;

    @Override
    public List<MedidaProducto> obtenerListadoMedidaProductos() {
        return medidaProductoDao.findByEstadoMedidaProductoIsTrue();
    }

    @Override
    public void guardarMedidaProducto(MedidaProducto medidaProducto) {
        medidaProducto.setEstadoMedidaProducto(true);
        medidaProductoDao.save(medidaProducto);
    }

    @Override
    public void eliminarMedidaProducto(MedidaProducto medidaProducto) {
        medidaProductoDao.delete(medidaProducto);
    }

    @Override
    public MedidaProducto encontrarMedidaProducto(MedidaProducto medidaProducto) {
        return medidaProductoDao.findById(medidaProducto.getIdMedidaProducto()).orElse(null);
    }

    @Override
    public void darBajaMedidaProducto(MedidaProducto medidaProducto) {
        MedidaProducto medidaProductoExistente = medidaProductoDao.findById(medidaProducto.getIdMedidaProducto()).orElse(null);
        if (medidaProductoExistente != null) {
            medidaProductoExistente.setEstadoMedidaProducto(false);
            medidaProductoDao.save(medidaProductoExistente);
        }
    }

}
