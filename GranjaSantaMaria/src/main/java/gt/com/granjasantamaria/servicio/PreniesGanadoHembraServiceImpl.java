package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.PreniesGanadoHembraDao;
import gt.com.granjasantamaria.modelo.PreniesGanadoHembra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PreniesGanadoHembraServiceImpl implements PreniesGanadoHembraService {

    @Autowired
    private PreniesGanadoHembraDao preniesGanadoHembraDao;

    @Override
    public Page<PreniesGanadoHembra> obtenerListadoPreniesGanadoHembraPaginado(Pageable pageable) {
        return preniesGanadoHembraDao.findAllByEstadoPreniesGanadoHembraIsTrue(pageable);
    }

    @Override
    public void guardarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        preniesGanadoHembra.setEstadoPreniesGanadoHembra(true);
        preniesGanadoHembraDao.save(preniesGanadoHembra);
    }

    @Override
    public void eliminarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        preniesGanadoHembraDao.delete(preniesGanadoHembra);
    }

    @Override
    public PreniesGanadoHembra encontrarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        return preniesGanadoHembraDao.findById(preniesGanadoHembra.getIdPreniesGanadoHembra()).orElse(null);
    }

    @Override
    public void darBajaPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        PreniesGanadoHembra preniesGanadoHembraExistente = preniesGanadoHembraDao.findById(preniesGanadoHembra.getIdPreniesGanadoHembra()).orElse(null);
        if (preniesGanadoHembraExistente != null) {
            preniesGanadoHembraExistente.setEstadoPreniesGanadoHembra(false);
            preniesGanadoHembraDao.save(preniesGanadoHembraExistente);
        }
    }

}
