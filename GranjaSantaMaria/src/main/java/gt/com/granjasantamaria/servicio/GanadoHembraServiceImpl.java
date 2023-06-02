package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.GanadoHembraDao;
import gt.com.granjasantamaria.modelo.GanadoHembra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class GanadoHembraServiceImpl implements GanadoHembraService {

    @Autowired
    private GanadoHembraDao ganadoHembraDao;

    @Override
    @Transactional(readOnly = true)
    public List<GanadoHembra> obtenerListadoGanadoHembras() {
        return ganadoHembraDao.findByEstadoGanadoHembraIsTrue();
    }

    @Override
    @Transactional
    public void guardarGanadoHembra(GanadoHembra ganadoHembra) {
        ganadoHembra.setEstadoGanadoHembra(true);
        ganadoHembraDao.save(ganadoHembra);
    }

    @Override
    @Transactional
    public void eliminarGanadoHembra(GanadoHembra ganadoHembra) {
        ganadoHembraDao.delete(ganadoHembra);
    }

    @Override
    @Transactional(readOnly = true)
    public GanadoHembra encontrarGanadoHembra(GanadoHembra ganadoHembra) {
        return ganadoHembraDao.findById(ganadoHembra.getIdGanadoHembra()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaGanadoHembra(GanadoHembra ganadoHembra) {
        GanadoHembra ganadoHembraExistente = ganadoHembraDao.findById(ganadoHembra.getIdGanadoHembra()).orElse(null);
        if (ganadoHembraExistente != null) {
            ganadoHembraExistente.setEstadoGanadoHembra(false);
            ganadoHembraDao.save(ganadoHembra);
        }
    }

}
