package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.HistorialClinicoMachoDao;
import gt.com.granjasantamaria.modelo.HistorialClinicoMacho;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistorialClinicioMachoServiceImpl implements HistorialClinicioMachoService {

    private final HistorialClinicoMachoDao historialClinicoMachoDao;

    @Autowired
    public HistorialClinicioMachoServiceImpl(HistorialClinicoMachoDao historialClinicoMachoDao) {
        this.historialClinicoMachoDao = historialClinicoMachoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistorialClinicoMacho> obtenerListadoHistorialClinicoMachos() {
        return historialClinicoMachoDao.findByEstadoHistorialClinicioMachoIsTrue();
    }

    @Override
    @Transactional
    public void guardarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho) {
        historialClinicoMacho.setEstadoHistorialClinicioMacho(true);
        historialClinicoMachoDao.save(historialClinicoMacho);
    }

    @Override
    @Transactional
    public void eliminarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho) {
        historialClinicoMachoDao.delete(historialClinicoMacho);
    }

    @Override
    @Transactional(readOnly = true)
    public HistorialClinicoMacho encontrarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho) {
        return historialClinicoMachoDao.findById(historialClinicoMacho.getIdHistorialClinicoMacho()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho) {
        HistorialClinicoMacho historialClinicoMachoExistente = historialClinicoMachoDao.findById(historialClinicoMacho.getIdHistorialClinicoMacho()).orElse(null);
        if (historialClinicoMachoExistente != null) {
            historialClinicoMachoExistente.setEstadoHistorialClinicioMacho(false);
            historialClinicoMachoDao.save(historialClinicoMachoExistente);
        }
    }

}
