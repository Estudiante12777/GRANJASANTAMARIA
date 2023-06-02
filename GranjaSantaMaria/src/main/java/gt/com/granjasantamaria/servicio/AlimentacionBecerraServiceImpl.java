package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.AlimentacionBecerraDao;
import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class AlimentacionBecerraServiceImpl implements AlimentacionBecerraService {

    @Autowired
    private AlimentacionBecerraDao alimentacionBecerraDao;

    @Override
    @Transactional(readOnly = true)
    public List<AlimentacionBecerra> obtenerListadoAlimentacionBecerras() {
        return alimentacionBecerraDao.findByEstadoAlimentacionBecerraIsTrue();
    }

    @Override
    @Transactional
    public void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        alimentacionBecerra.setEstadoAlimentacionBecerra(true);
        alimentacionBecerraDao.save(alimentacionBecerra);
    }

    @Override
    @Transactional
    public void eliminarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        alimentacionBecerraDao.delete(alimentacionBecerra);
    }

    @Override
    @Transactional(readOnly = true)
    public AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        return alimentacionBecerraDao.findById(alimentacionBecerra.getIdAlimentacionBecerra()).orElse(null);
    }

    @Override
    public void darDeBajaAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        AlimentacionBecerra alimentacionBecerraExistente = alimentacionBecerraDao.findById(alimentacionBecerra.getIdAlimentacionBecerra()).orElse(null);
        if (alimentacionBecerraExistente != null) {
            alimentacionBecerraExistente.setEstadoAlimentacionBecerra(false);
            alimentacionBecerraDao.save(alimentacionBecerraExistente);
        }
    }

}
