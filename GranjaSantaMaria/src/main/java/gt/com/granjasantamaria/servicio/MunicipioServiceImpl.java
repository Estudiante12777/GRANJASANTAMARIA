package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MunicipioServiceImpl implements MunicipioService {

    private final MunicipioDao municipioDao;

    @Autowired
    public MunicipioServiceImpl(MunicipioDao municipioDao) {
        this.municipioDao = municipioDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Municipio> listadoMunicipios() {
        return municipioDao.findByEstadoMunicipioIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Municipio> listadoMunicipioPaginado(Pageable pageable) {
        return municipioDao.findAllByEstadoMunicipioIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarMunicipio(Municipio municipio) {
        municipio.setEstadoMunicipio(true);
        municipioDao.save(municipio);
    }

    @Override
    @Transactional(readOnly = true)
    public Municipio encontrarMunicipio(Municipio municipio) {
        return municipioDao.findById(municipio.getIdMunicipio()).orElse(municipio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Municipio> obtenerMunicipiosPorDepartamento(Long idDepatamento) {
        return municipioDao.obtenerMunicipiosPorDepartamento(idDepatamento);
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
