package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.AlimentacionBecerraDao;
import gt.com.granjasantamaria.modelo.AlimentacionBecerra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlimentacionBecerraServiceImpl implements AlimentacionBecerraService {

    private final AlimentacionBecerraDao alimentacionBecerraDao;

    @Autowired
    public AlimentacionBecerraServiceImpl(AlimentacionBecerraDao alimentacionBecerraDao) {
        this.alimentacionBecerraDao = alimentacionBecerraDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlimentacionBecerra> obtenerAlimentacionBecerraPaginado(Pageable pageable) {
        return alimentacionBecerraDao.findAllByEstadoAlimentacionBecerraIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        alimentacionBecerra.setEstadoAlimentacionBecerra(true);
        alimentacionBecerraDao.save(alimentacionBecerra);
    }

    @Override
    @Transactional(readOnly = true)
    public AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        return alimentacionBecerraDao.findById(alimentacionBecerra.getIdAlimentacionBecerra()).orElse(null);
    }

    @Override
    @Transactional
    public void darDeBajaAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        AlimentacionBecerra alimentacionBecerraExistente = alimentacionBecerraDao.findById(alimentacionBecerra.getIdAlimentacionBecerra()).orElse(null);
        if (alimentacionBecerraExistente != null) {
            alimentacionBecerraExistente.setEstadoAlimentacionBecerra(false);
            alimentacionBecerraDao.save(alimentacionBecerraExistente);
        }
    }

}
