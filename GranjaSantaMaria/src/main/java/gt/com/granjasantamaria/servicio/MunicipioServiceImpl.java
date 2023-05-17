package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.MunicipioDao;
import gt.com.granjasantamaria.modelo.Municipio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class MunicipioServiceImpl implements MunicipioService {

    @Autowired
    private MunicipioDao municipioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Municipio> listadoMunicipios() {
        return municipioDao.findByEstadoMunicipioIsTrue();
    }

    @Override
    @Transactional
    public void guardarMunicipio(Municipio municipio) {
        municipio.setEstadoMunicipio(true);
        municipioDao.save(municipio);
    }

    @Override
    @Transactional
    public void eliminarMunicipio(Municipio municipio) {
        municipioDao.delete(municipio);
    }

    @Override
    @Transactional(readOnly = true)
    public Municipio encontrarMunicipio(Municipio municipio) {
        return municipioDao.findById(municipio.getIdMunicipio()).orElse(municipio);
    }

    @Override
    @Transactional
    public void darBajaMunicipio(Municipio municipio) {
        Municipio municipioExistente = municipioDao.findById(municipio.getIdMunicipio()).orElse(null);
        if (municipioExistente != null) {
            municipioExistente.setEstadoMunicipio(false);
            municipioDao.save(municipioExistente);
        }
    }

}
