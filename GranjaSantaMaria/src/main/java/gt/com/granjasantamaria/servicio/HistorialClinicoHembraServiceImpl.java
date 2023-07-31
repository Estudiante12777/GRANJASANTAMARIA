package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.HistorialClinicoHembraDao;
import gt.com.granjasantamaria.modelo.HistorialClinicoHembra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistorialClinicoHembraServiceImpl implements HistorialClinicoHembraService {

    private final HistorialClinicoHembraDao historialClinicoHembraDao;

    @Autowired
    public HistorialClinicoHembraServiceImpl(HistorialClinicoHembraDao historialClinicoHembraDao) {
        this.historialClinicoHembraDao = historialClinicoHembraDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistorialClinicoHembra> obtenerListadoHistorialClinicoHembras() {
        return historialClinicoHembraDao.findByEstadoHistorialClinicoHembraIsTrue();
    }

    @Override
    @Transactional
    public void guardarHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra) {
        historialClinicoHembra.setEstadoHistorialClinicoHembra(true);
        historialClinicoHembraDao.save(historialClinicoHembra);
    }

    @Override
    @Transactional(readOnly = true)
    public HistorialClinicoHembra encontrarHistorialClincioHembra(HistorialClinicoHembra historialClinicoHembra) {
        return historialClinicoHembraDao.findById(historialClinicoHembra.getIdHistorialClinicoHembra()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra) {
        HistorialClinicoHembra historialClinicoHembraExistente = historialClinicoHembraDao.findById(historialClinicoHembra.getIdHistorialClinicoHembra()).orElse(null);
        if (historialClinicoHembraExistente != null) {
            historialClinicoHembraExistente.setEstadoHistorialClinicoHembra(false);
            historialClinicoHembraDao.save(historialClinicoHembraExistente);
        }
    }

}
