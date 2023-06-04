package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DiarioGastoGranjaDao;
import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class DiarioGastoGranjaServiceImpl implements DiarioGastoGranjaService {

    @Autowired
    private DiarioGastoGranjaDao diarioGastoGranjaDao;

    @Override
    @Transactional(readOnly = true)
    public List<DiarioGastoGranja> obtenerListadoDiarioGastosGranja() {
        return diarioGastoGranjaDao.findByEstadoDiarioGastoGranjaIsTrue();
    }

    @Override
    @Transactional
    public void guardarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        diarioGastoGranja.setEstadoDiarioGastoGranja(true);
        diarioGastoGranjaDao.save(diarioGastoGranja);
    }

    @Override
    @Transactional
    public void eliminarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        diarioGastoGranjaDao.delete(diarioGastoGranja);
    }

    @Override
    @Transactional(readOnly = true)
    public DiarioGastoGranja encontrarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        return diarioGastoGranjaDao.findById(diarioGastoGranja.getIdDiarioGastoGranja()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        DiarioGastoGranja diarioGastoGranjaExistente = diarioGastoGranjaDao.findById(diarioGastoGranja.getIdDiarioGastoGranja()).orElse(null);
        if (diarioGastoGranjaExistente != null) {
            diarioGastoGranjaExistente.setEstadoDiarioGastoGranja(false);
            diarioGastoGranjaDao.save(diarioGastoGranjaExistente);
        }
    }

}
