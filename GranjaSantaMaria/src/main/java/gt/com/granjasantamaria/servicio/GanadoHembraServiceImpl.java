package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.GanadoHembraDao;
import gt.com.granjasantamaria.modelo.GanadoHembra;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GanadoHembraServiceImpl implements GanadoHembraService {

    private final GanadoHembraDao ganadoHembraDao;

    @Autowired
    public GanadoHembraServiceImpl(GanadoHembraDao ganadoHembraDao) {
        this.ganadoHembraDao = ganadoHembraDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GanadoHembra> obtenerListadoGanadosHembra() {
        return ganadoHembraDao.findByEstadoGanadoHembraIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GanadoHembra> obtenerGanadoHembraPaginado(Pageable pageable) {
        return ganadoHembraDao.findAllByEstadoGanadoHembraIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarGanadoHembra(GanadoHembra ganadoHembra) {
        ganadoHembra.setEstadoGanadoHembra(true);
        ganadoHembraDao.save(ganadoHembra);
    }

    @Override
    @Transactional(readOnly = true)
    public GanadoHembra encontrarGanadoHembra(GanadoHembra ganadoHembra) {
        return ganadoHembraDao.findById(ganadoHembra.getIdGanadoHembra()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaGanadoHembra(GanadoHembra ganadoHembra) {
        GanadoHembra ganadoHembraExistente = ganadoHembraDao.findById(ganadoHembra.getIdGanadoHembra()).orElse(null);
        if (ganadoHembraExistente != null) {
            ganadoHembraExistente.setEstadoGanadoHembra(false);
            ganadoHembraDao.save(ganadoHembraExistente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GanadoHembra encontrarGanadoHembraPorId(Long id) {
        Optional<GanadoHembra> ganadoHembraOptional = ganadoHembraDao.findById(id);
        return ganadoHembraOptional.orElse(null);
    }

}
