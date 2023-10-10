package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.AlimentacionBecerraDao;
import gt.com.granjasantamaria.modelo.AlimentacionBecerra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class AlimentacionBecerraServiceImpl implements AlimentacionBecerraService {

    private final AlimentacionBecerraDao alimentacionBecerraDao;

    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Page<Object[]> obtenerAlimentacionBecerraPaginado(Pageable pageable) {
        return alimentacionBecerraDao.obtenerDatosAlimentacionConRelaciones(pageable);
    }

    @Override
    @Transactional
    public void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        alimentacionBecerra.setEstadoAlimentacionBecerra(true);
        alimentacionBecerraDao.save(alimentacionBecerra);
    }

    @Override
    @Transactional
    public void darDeBajaAlimentacionBecerra(Long idAlimentacionBecerra) {
        alimentacionBecerraDao.darDeBajaPorId(idAlimentacionBecerra);
        entityManager.flush();
    }

}
