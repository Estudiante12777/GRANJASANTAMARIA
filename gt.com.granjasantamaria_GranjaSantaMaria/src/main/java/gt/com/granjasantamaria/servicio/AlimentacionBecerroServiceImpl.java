package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.AlimentacionBecerroDao;
import gt.com.granjasantamaria.modelo.AlimentacionBecerro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class AlimentacionBecerroServiceImpl implements AlimentacionBecerroService {

    @Autowired
    private AlimentacionBecerroDao alimentacionBecerroDao;

    @Override
    @Transactional(readOnly = true)
    public List<AlimentacionBecerro> obtenerListadoAlimentacionBecerros() {
        return alimentacionBecerroDao.findByEstadoAlimentacionBecerroIsTrue();
    }

    @Override
    @Transactional
    public void guardarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        alimentacionBecerro.setEstadoAlimentacionBecerro(true);
        alimentacionBecerroDao.save(alimentacionBecerro);
    }

    @Override
    @Transactional
    public void eliminarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        alimentacionBecerroDao.delete(alimentacionBecerro);
    }

    @Override
    @Transactional(readOnly = true)
    public AlimentacionBecerro encontrarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        return alimentacionBecerroDao.findById(alimentacionBecerro.getIdAlimentacionBecerro()).orElse(null);
    }

    @Override
    @Transactional
    public void darDeBajaAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        AlimentacionBecerro alimentacionBecerroExistente = alimentacionBecerroDao.findById(alimentacionBecerro.getIdAlimentacionBecerro()).orElse(null);
        if (alimentacionBecerroExistente != null) {
            alimentacionBecerroExistente.setEstadoAlimentacionBecerro(false);
            alimentacionBecerroDao.save(alimentacionBecerroExistente);
        }
    }

}
