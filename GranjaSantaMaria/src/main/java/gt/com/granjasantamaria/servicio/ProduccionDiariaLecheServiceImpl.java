package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.ProduccionDiariaLecheDao;
import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
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
    public List<ProduccionDiariaLeche> listaProduccionDiariaLeche() {
        LocalDate fechaActual = LocalDate.now();
        java.sql.Date fechaActualSql = java.sql.Date.valueOf(fechaActual);
        return produccionDiariaLecheDao.findByFechaProduccionLecheAndEstadoProduccionDiariaLecheTrue(fechaActualSql);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduccionDiariaLeche> listaTotalProduccionDiariaLeche() {
        LocalDate fechaActual = LocalDate.now();
        java.sql.Date fechaActualSql = java.sql.Date.valueOf(fechaActual);
        return produccionDiariaLecheDao.findByFechaProduccionLecheAndEstadoProduccionDiariaLecheTrue(fechaActualSql);
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
    public void darBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        ProduccionDiariaLeche produccionDiariaLecheExistente = produccionDiariaLecheDao.findById(produccionDiariaLeche.getIdProduccionDiariaLeche()).orElse(null);
        if (produccionDiariaLecheExistente != null) {
            produccionDiariaLecheExistente.setEstadoProduccionDiariaLeche(false);
            produccionDiariaLecheDao.save(produccionDiariaLecheExistente);
        }
    }

}
