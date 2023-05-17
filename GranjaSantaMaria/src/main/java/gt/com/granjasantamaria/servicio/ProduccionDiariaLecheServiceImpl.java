package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class ProduccionDiariaLecheServiceImpl implements ProduccionDiariaLecheService {

    @Autowired
    private ProduccionDiariaLecheDao produccionDiariaLecheDao;

    @Override
    @Transactional(readOnly = true)
    public List<ProduccionDiariaLeche> obtenerListaProduccionDiariaLeche() {
        LocalDate fechaActual = LocalDate.now();
        return produccionDiariaLecheDao.findByFechaProduccionLecheAndEstadoProduccionDiariaLecheIsTrue(fechaActual);
    }

    @Override
    public List<ProduccionDiariaLeche> obtenerListaTotalProduccionLeche() {
        return produccionDiariaLecheDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduccionDiariaLeche> obtenerListaTotalProduccionDiariaLeche() {
        LocalDate fechaActual = LocalDate.now();
        return produccionDiariaLecheDao.findByFechaProduccionLecheAndEstadoProduccionDiariaLecheIsTrue(fechaActual);
    }

    @Override
    public void guardarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        produccionDiariaLeche.setEstadoProduccionDiariaLeche(true);
        produccionDiariaLecheDao.save(produccionDiariaLeche);
    }

    @Override
    public void eliminarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        produccionDiariaLecheDao.delete(produccionDiariaLeche);
    }

    @Override
    public ProduccionDiariaLeche encontrarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        return produccionDiariaLecheDao.findById(produccionDiariaLeche.getIdProduccionDiariaLeche()).orElse(null);
    }

    @Override
    public List<ProduccionDiariaLeche> encontrarTotalProduccionFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return produccionDiariaLecheDao.findByFechaProduccionLecheBetween(fechaInicio, fechaFin);
    }

    @Override
    public void darDeBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        ProduccionDiariaLeche produccionDiariaLecheExistente = produccionDiariaLecheDao.findById(produccionDiariaLeche.getIdProduccionDiariaLeche()).orElse(null);
        if (produccionDiariaLecheExistente != null) {
            produccionDiariaLecheExistente.setEstadoProduccionDiariaLeche(false);
            produccionDiariaLecheDao.save(produccionDiariaLecheExistente);
        }
    }

}
