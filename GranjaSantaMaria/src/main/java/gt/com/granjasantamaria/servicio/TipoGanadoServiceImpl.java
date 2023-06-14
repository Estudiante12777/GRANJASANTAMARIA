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
public class TipoGanadoServiceImpl implements TipoGanadoService {

    @Autowired
    private TipoGanadoDao tipoGanadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<TipoGanado> listadoTiposGanado() {
        return tipoGanadoDao.findByEstadoTipoGanadoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoGanado> obtenerListadoTipoGanadoPaginado(Pageable pageable) {
        return tipoGanadoDao.findAllByEstadoTipoGanadoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarTipoGanado(TipoGanado tipoGanado) {
        tipoGanado.setEstadoTipoGanado(true);
        tipoGanadoDao.save(tipoGanado);
    }

    @Override
    @Transactional
    public void eliminarTipoGanado(TipoGanado tipoGanado) {
        tipoGanadoDao.delete(tipoGanado);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoGanado encontrarTipoGanado(TipoGanado tipoGanado) {
        return tipoGanadoDao.findById(tipoGanado.getIdTipoGanado()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaTipoGanado(TipoGanado tipoGanado) {
        TipoGanado tipoGanadoExistente = tipoGanadoDao.findById(tipoGanado.getIdTipoGanado()).orElse(null);
        if (tipoGanadoExistente != null) {
            tipoGanadoExistente.setEstadoTipoGanado(false);
            tipoGanadoDao.save(tipoGanadoExistente);
        }
    }

}
