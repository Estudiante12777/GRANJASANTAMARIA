package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DietaTerneraTerneroDao;
import gt.com.granjasantamaria.modelo.DietaTerneraTernero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DietaTerneraTerneroServiceImpl implements DietaTerneraTerneroService {

    private final DietaTerneraTerneroDao dietaTerneraTerneroDao;

    @Autowired
    public DietaTerneraTerneroServiceImpl(DietaTerneraTerneroDao dietaTerneraTerneroDao) {
        this.dietaTerneraTerneroDao = dietaTerneraTerneroDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DietaTerneraTernero> obtenerListadoDietaTerneraTerneroPaginado(Pageable pageable) {
        return dietaTerneraTerneroDao.findAllByEstadoDietaTerneraTerneroIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero) {
        dietaTerneraTernero.setEstadoDietaTerneraTernero(true);
        dietaTerneraTerneroDao.save(dietaTerneraTernero);
    }

    @Override
    @Transactional
    public void eliminarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero) {
        dietaTerneraTerneroDao.delete(dietaTerneraTernero);
    }

    @Override
    @Transactional(readOnly = true)
    public DietaTerneraTernero encontrarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero) {
        return dietaTerneraTerneroDao.findById(dietaTerneraTernero.getIdDietaTerneraTernero()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero) {
        DietaTerneraTernero dietaTerneraTerneroExistente = dietaTerneraTerneroDao.findById(dietaTerneraTernero.getIdDietaTerneraTernero()).orElse(null);
        if (dietaTerneraTerneroExistente != null) {
            dietaTerneraTerneroExistente.setEstadoDietaTerneraTernero(false);
            dietaTerneraTerneroDao.save(dietaTerneraTerneroExistente);
        }
    }

}
