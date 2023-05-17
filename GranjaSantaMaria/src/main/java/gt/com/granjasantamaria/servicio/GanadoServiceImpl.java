package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.GanadoDao;
import gt.com.granjasantamaria.modelo.Ganado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class GanadoServiceImpl implements GanadoService {

    @Autowired
    private GanadoDao ganadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ganado> listadoGanado() {
        return ganadoDao.findByEstadoGanadoIsTrue();
    }

    @Override
    @Transactional
    public void guardarGanado(Ganado ganado) {
        ganado.setEstadoGanado(true);
        ganadoDao.save(ganado);
    }

    @Override
    @Transactional
    public void eliminarGanado(Ganado ganado) {
        ganadoDao.delete(ganado);
    }

    @Override
    @Transactional(readOnly = true)
    public Ganado encontrarGanado(Ganado ganado) {
        return ganadoDao.findById(ganado.getIdGanado()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaGanado(Ganado ganado) {
        Ganado ganadoExistente = ganadoDao.findById(ganado.getIdGanado()).orElse(null);
        if (ganadoExistente != null) {
            ganadoExistente.setEstadoGanado(false);
            ganadoDao.save(ganadoExistente);
        }
    }

}
