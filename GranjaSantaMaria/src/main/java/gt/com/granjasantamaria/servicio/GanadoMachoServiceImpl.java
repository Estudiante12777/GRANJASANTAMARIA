package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.GanadoMachoDao;
import gt.com.granjasantamaria.modelo.GanadoMacho;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GanadoMachoServiceImpl implements GanadoMachoService {

    private final GanadoMachoDao ganadoMachoDao;

    @Autowired
    public GanadoMachoServiceImpl(GanadoMachoDao ganadoMachoDao) {
        this.ganadoMachoDao = ganadoMachoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GanadoMacho> obtenerListadoGanadoMachos() {
        return ganadoMachoDao.findByEstadoGanadoMachoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GanadoMacho> obtenerGanadoMachoPaginado(Pageable pageable) {
        return ganadoMachoDao.findAllByEstadoGanadoMachoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarGanadoMacho(GanadoMacho ganadoMacho) {
        ganadoMacho.setEstadoGanadoMacho(true);
        ganadoMachoDao.save(ganadoMacho);
    }

    @Override
    @Transactional
    public void eliminarGanadoMacho(GanadoMacho ganadoMacho) {
        ganadoMachoDao.delete(ganadoMacho);
    }

    @Override
    @Transactional(readOnly = true)
    public GanadoMacho encontrarGanadoMacho(GanadoMacho ganadoMacho) {
        return ganadoMachoDao.findById(ganadoMacho.getIdGanadoMacho()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaGanadoMacho(GanadoMacho ganadoMacho) {
        GanadoMacho ganadoMachoExistente = ganadoMachoDao.findById(ganadoMacho.getIdGanadoMacho()).orElse(null);
        if (ganadoMachoExistente != null) {
            ganadoMachoExistente.setEstadoGanadoMacho(false);
            ganadoMachoDao.save(ganadoMachoExistente);
        }
    }

}
