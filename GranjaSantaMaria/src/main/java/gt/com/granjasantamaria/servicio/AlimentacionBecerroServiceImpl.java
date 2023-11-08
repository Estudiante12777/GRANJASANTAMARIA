package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.AlimentacionBecerroDao;
import gt.com.granjasantamaria.modelo.AlimentacionBecerro;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlimentacionBecerroServiceImpl implements AlimentacionBecerroService {

    private final AlimentacionBecerroDao alimentacionBecerroDao;

    @Override
    @Transactional(readOnly = true)
    public Page<AlimentacionBecerro> obtenerListadoAlimentacionBecerroPaginado(Pageable pageable) {
        return alimentacionBecerroDao.findAllByEstadoAlimentacionBecerroIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        alimentacionBecerro.setEstadoAlimentacionBecerro(true);
        alimentacionBecerroDao.save(alimentacionBecerro);
    }

    @Override
    @Transactional(readOnly = true)
    public AlimentacionBecerro encontrarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        return alimentacionBecerroDao.findById(alimentacionBecerro.getIdAlimentacionBecerro()).orElse(null);
    }

    @Override
    @Transactional
    public void darDeBajaAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        AlimentacionBecerro alimentacionBecerroExistente = alimentacionBecerroDao.findById(alimentacionBecerro.getIdAlimentacionBecerro()).orElse(null);
        if (alimentacionBecerroExistente != null) {
            alimentacionBecerroExistente.setEstadoAlimentacionBecerro(false);
            alimentacionBecerroDao.save(alimentacionBecerroExistente);
        }
    }

}
