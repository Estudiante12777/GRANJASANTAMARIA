package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DetalleHistorialClinicoMachoDao;
import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class DetalleHistorialClinicoMachoServiceImpl implements DetalleHistorialClinicoMachoService {

    @Autowired
    private DetalleHistorialClinicoMachoDao detalleHistorialClinicoMachoDao;

    @Override
    @Transactional(readOnly = true)
    public List<DetalleHistorialClinicoMacho> obtenerListadoDetalleHistorialClinicoMachos() {
        return detalleHistorialClinicoMachoDao.findByEstadoDetalleHistorialClinicoMachoIsTrue();
    }

    @Override
    @Transactional
    public void guardarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho) {
        detalleHistorialClinicoMacho.setEstadoDetalleHistorialClinicoMacho(true);
        detalleHistorialClinicoMachoDao.save(detalleHistorialClinicoMacho);
    }

    @Override
    @Transactional
    public void eliminarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho) {
        detalleHistorialClinicoMachoDao.delete(detalleHistorialClinicoMacho);
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleHistorialClinicoMacho encontrarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho) {
        return detalleHistorialClinicoMachoDao.findById(detalleHistorialClinicoMacho.getIdDetalleHistorialClinicoMacho()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho) {
        DetalleHistorialClinicoMacho detalleHistorialClinicoMachoExistente = detalleHistorialClinicoMachoDao.findById(detalleHistorialClinicoMacho.getIdDetalleHistorialClinicoMacho()).orElse(null);
        if (detalleHistorialClinicoMachoExistente != null) {
            detalleHistorialClinicoMachoExistente.setEstadoDetalleHistorialClinicoMacho(false);
            detalleHistorialClinicoMachoDao.save(detalleHistorialClinicoMachoExistente);
        }
    }

}
