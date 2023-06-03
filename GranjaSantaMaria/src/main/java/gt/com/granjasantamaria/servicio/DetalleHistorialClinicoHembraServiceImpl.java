package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DetalleHistorialClinicoHembraDao;
import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoHembra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class DetalleHistorialClinicoHembraServiceImpl implements DetalleHistorialClinicoHembraService {

    @Autowired
    private DetalleHistorialClinicoHembraDao detalleHistorialClinicoHembraDao;

    @Override
    @Transactional(readOnly = true)
    public List<DetalleHistorialClinicoHembra> obtenerListadoDetalleHistorialClinicoHembras() {
        return detalleHistorialClinicoHembraDao.findByEstadoDetalleHistorialClinicoHembraIsTrue();
    }

    @Override
    public void guardarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        detalleHistorialClinicoHembra.setEstadoDetalleHistorialClinicoHembra(true);
        detalleHistorialClinicoHembraDao.save(detalleHistorialClinicoHembra);
    }

    @Override
    public void eliminarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        detalleHistorialClinicoHembraDao.delete(detalleHistorialClinicoHembra);
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleHistorialClinicoHembra encontrarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        return detalleHistorialClinicoHembraDao.findById(detalleHistorialClinicoHembra.getIdDetalleHistorialClinicoHembra()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        DetalleHistorialClinicoHembra detalleHistorialClinicoHembraExistente = detalleHistorialClinicoHembraDao.findById(detalleHistorialClinicoHembra.getIdDetalleHistorialClinicoHembra()).orElse(null);
        if (detalleHistorialClinicoHembraExistente != null) {
            detalleHistorialClinicoHembraExistente.setEstadoDetalleHistorialClinicoHembra(false);
            detalleHistorialClinicoHembraDao.save(detalleHistorialClinicoHembra);
        }
    }

}
