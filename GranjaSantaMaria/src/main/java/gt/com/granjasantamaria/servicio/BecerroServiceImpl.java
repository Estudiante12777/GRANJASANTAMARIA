package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.BecerroDao;
import gt.com.granjasantamaria.modelo.Becerro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BecerroServiceImpl implements BecerroService {

    private final BecerroDao becerroDao;

    @Autowired
    public BecerroServiceImpl(BecerroDao becerroDao) {
        this.becerroDao = becerroDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Becerro> listadoBecerros() {
        return becerroDao.findByEstadoRelacionMadreBecerroIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Becerro> listadoBecerrosPaginado(Pageable pageable) {
        return becerroDao.findAllByEstadoRelacionMadreBecerroIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarBecerro(Becerro becerro) {
        becerro.setEstadoRelacionMadreBecerro(true);
        becerroDao.save(becerro);
    }

    @Override
    @Transactional(readOnly = true)
    public Becerro encontrarBecerro(Becerro becerro) {
        return becerroDao.findById(becerro.getIdRelacionMadreBecerro()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaBecerro(Becerro becerro) {
        Becerro becerroExistente = becerroDao.findById(becerro.getIdRelacionMadreBecerro()).orElse(null);
        if (becerroExistente != null) {
            becerroExistente.setEstadoRelacionMadreBecerro(false);
            becerroDao.save(becerroExistente);
        }
    }

}
