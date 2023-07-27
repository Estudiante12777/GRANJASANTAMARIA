package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DetalleHistorialClinicoMachoDao;
import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleHistorialClinicoMachoServiceImpl implements DetalleHistorialClinicoMachoService {

    private final DetalleHistorialClinicoMachoDao detalleHistorialClinicoMachoDao;

    @Autowired
    public DetalleHistorialClinicoMachoServiceImpl(DetalleHistorialClinicoMachoDao detalleHistorialClinicoMachoDao) {
        this.detalleHistorialClinicoMachoDao = detalleHistorialClinicoMachoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetalleHistorialClinicoMacho> obtenerListadoDetalleHistorialClinicoMachos(Long idHistorialClinicoMacho, Pageable pageable) {
        return detalleHistorialClinicoMachoDao.findAllByIdHistorialClinicoMachoAndEstadoDetalleHistorialClinicoMachoIsTrue(idHistorialClinicoMacho, pageable);
    }

    @Override
    @Transactional
    public void guardarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho) {
        detalleHistorialClinicoMacho.setEstadoDetalleHistorialClinicoMacho(true);
        detalleHistorialClinicoMachoDao.save(detalleHistorialClinicoMacho);
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
