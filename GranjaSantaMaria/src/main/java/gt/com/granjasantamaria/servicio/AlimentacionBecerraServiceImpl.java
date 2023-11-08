package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.AlimentacionBecerraDao;
import gt.com.granjasantamaria.dao.BecerraDao;
import gt.com.granjasantamaria.modelo.AlimentacionBecerra;

import gt.com.granjasantamaria.modelo.Becerra;
import gt.com.granjasantamaria.modelo.GanadoHembra;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlimentacionBecerraServiceImpl implements AlimentacionBecerraService {
    private final AlimentacionBecerraDao alimentacionBecerraDao;
    private final BecerraDao becerraDao;

    @Override
    @Transactional(readOnly = true)
    public Page<AlimentacionBecerra> listadoAlimentacionBecerras(Pageable pageable) {
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
    public void darBajaAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        AlimentacionBecerra alimentacionBecerraExistente = alimentacionBecerraDao.findById(alimentacionBecerra.getIdAlimentacionBecerra()).orElse(null);
        if (alimentacionBecerraExistente != null) {
            alimentacionBecerraExistente.setEstadoAlimentacionBecerra(false);
            alimentacionBecerraDao.save(alimentacionBecerraExistente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Becerra> obtenerRelacionMadreBecerra(AlimentacionBecerra alimentacionBecerra) {
        GanadoHembra vaca = alimentacionBecerra.getBecerra().getMadre();
        return becerraDao.findByMadre(vaca);
    }

    @Override
    @Transactional(readOnly = true)
    public AlimentacionBecerra obtenerProduccionDiariaLechePorIdAlimentacionBecerra(Long idAlimentacionBecerra) {
        return alimentacionBecerraDao.findByIdProduccionDiariaLeche(idAlimentacionBecerra);
    }
}
