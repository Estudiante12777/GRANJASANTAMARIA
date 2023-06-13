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
public class RazaGanadoServiceImpl implements RazaGanadoService {

    @Autowired
    private RazaGanadoDao razaGanadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<RazaGanado> listadoRazasGanado() {
        return razaGanadoDao.findByEstadoRazaGandoIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RazaGanado> obtenerListadoRazaGanadoPaginado(Pageable pageable) {
        return  razaGanadoDao.findAllByEstadoRazaGandoIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarRazaGanado(RazaGanado razaGanado) {
        razaGanado.setEstadoRazaGando(true);
        razaGanadoDao.save(razaGanado);
    }

    @Override
    @Transactional
    public void eliminarRazaGanado(RazaGanado razaGanado) {
        razaGanadoDao.delete(razaGanado);
    }

    @Override
    @Transactional(readOnly = true)
    public RazaGanado encontrarRazaGando(RazaGanado razaGanado) {
        return razaGanadoDao.findById(razaGanado.getIdRazaGanado()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaRazaGanado(RazaGanado razaGanado) {
        RazaGanado razaGanadoExistente = razaGanadoDao.findById(razaGanado.getIdRazaGanado()).orElse(null);
        if (razaGanadoExistente != null) {
            razaGanadoExistente.setEstadoRazaGando(false);
            razaGanadoDao.save(razaGanadoExistente);
        }
    }

}
