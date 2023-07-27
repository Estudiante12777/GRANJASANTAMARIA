package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.PreniesGanadoHembraDao;
import gt.com.granjasantamaria.modelo.GanadoHembra;
import gt.com.granjasantamaria.modelo.PreniesGanadoHembra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PreniesGanadoHembraServiceImpl implements PreniesGanadoHembraService {

    private final PreniesGanadoHembraDao preniesGanadoHembraDao;

    @Autowired
    public PreniesGanadoHembraServiceImpl(PreniesGanadoHembraDao preniesGanadoHembraDao) {
        this.preniesGanadoHembraDao = preniesGanadoHembraDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PreniesGanadoHembra> obtenerListadoPreniesGanadoHembraPaginado(Pageable pageable) {
        return preniesGanadoHembraDao.findAllByEstadoPreniesGanadoHembraIsTrue(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreniesGanadoHembra> obtenerListadoPreniesGanadoHembra() {
        return preniesGanadoHembraDao.findByEstadoPreniesGanadoHembraIsTrue();
    }

    @Override
    @Transactional
    public void guardarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        preniesGanadoHembra.setEstadoPreniesGanadoHembra(true);
        preniesGanadoHembraDao.save(preniesGanadoHembra);
    }

    @Override
    @Transactional
    public void eliminarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        preniesGanadoHembraDao.delete(preniesGanadoHembra);
    }

    @Override
    @Transactional(readOnly = true)
    public PreniesGanadoHembra encontrarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        return preniesGanadoHembraDao.findById(preniesGanadoHembra.getIdPreniesGanadoHembra()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        PreniesGanadoHembra preniesGanadoHembraExistente = preniesGanadoHembraDao.findById(preniesGanadoHembra.getIdPreniesGanadoHembra()).orElse(null);
        if (preniesGanadoHembraExistente != null) {
            preniesGanadoHembraExistente.setEstadoPreniesGanadoHembra(false);
            preniesGanadoHembraDao.save(preniesGanadoHembraExistente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreniesGanadoHembra> obtenerListadoPreniesGanadoHembraPorGanadoHembra(GanadoHembra ganadoHembra) {
        return preniesGanadoHembraDao.findByGanadoHembra(ganadoHembra);
    }

}
