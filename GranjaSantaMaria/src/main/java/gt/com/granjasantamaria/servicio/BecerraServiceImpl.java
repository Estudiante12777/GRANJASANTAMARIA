package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.BecerraDao;
import gt.com.granjasantamaria.modelo.Becerra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BecerraServiceImpl implements BecerraService {

    private final BecerraDao becerraDao;

    @Autowired
    public BecerraServiceImpl(BecerraDao becerraDao) {
        this.becerraDao = becerraDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Becerra> listadoBecerras() {
        return becerraDao.findByEstadoRelacionMadreBecerraIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Becerra> listadoBecerrasPaginado(Pageable pageable) {
        return becerraDao.findAllByEstadoRelacionMadreBecerraIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarBecerra(Becerra becerra) {
        becerra.setEstadoRelacionMadreBecerra(true);
        becerraDao.save(becerra);
    }

    @Override
    @Transactional(readOnly = true)
    public Becerra encontrarBecerra(Becerra becerra) {
        return becerraDao.findById(becerra.getIdRelacionMadreBecerra()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaBecerra(Becerra becerra) {
        Becerra becerraExistente = becerraDao.findById(becerra.getIdRelacionMadreBecerra()).orElse(null);
        if (becerraExistente != null) {
            becerraExistente.setEstadoRelacionMadreBecerra(false);
            becerraDao.save(becerraExistente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Becerra encontrarBecerraPorId(Long id_relacion_madre_becerra) {
        Optional<Becerra> becerraOptional = becerraDao.findByBecerraIdGanadoHembra(id_relacion_madre_becerra);
        System.out.println(becerraOptional);
        return becerraOptional.orElse(null);
    }

}
