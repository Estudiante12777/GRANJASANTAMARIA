package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.PaisDao;
import gt.com.granjasantamaria.modelo.Pais;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisDao paisDao;

    @Override
    @Transactional(readOnly = true)
    public List<Pais> listadoPais() {
        return paisDao.findByEstadoPaisIsTrue();
    }

    @Override
    @Transactional
    public void guardarPais(Pais pais) {
        pais.setEstadoPais(true);
        paisDao.save(pais);
    }

    @Override
    @Transactional
    public void eliminarPais(Pais pais) {
        paisDao.delete(pais);
    }

    @Override
    @Transactional(readOnly = true)
    public Pais encontrarPais(Pais pais) {
        return paisDao.findById(pais.getIdPais()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaPais(Pais pais) {
        Pais paisExistente = paisDao.findById(pais.getIdPais()).orElse(null);
        if (paisExistente != null) {
            paisExistente.setEstadoPais(false);
            paisDao.save(paisExistente);
        }
    }

}
