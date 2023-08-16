package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProduccionDiariaLecheServiceImpl implements ProduccionDiariaLecheService {

    private final BecerraDao becerraDao;

    private final BecerroDao becerroDao;

    private final ProduccionDiariaLecheDao produccionDiariaLecheDao;

    @Autowired
    public ProduccionDiariaLecheServiceImpl(BecerraDao becerraDao, BecerroDao becerroDao, ProduccionDiariaLecheDao produccionDiariaLecheDao) {
        this.becerraDao = becerraDao;
        this.becerroDao = becerroDao;
        this.produccionDiariaLecheDao = produccionDiariaLecheDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduccionDiariaLeche> obtenerListaProduccionDiariaLeche() {
        LocalDate fechaActual = LocalDate.now();
        return produccionDiariaLecheDao.findByFechaProduccionLecheAndEstadoProduccionDiariaLecheIsTrue(fechaActual);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public Page<ProduccionDiariaLeche> obtenerProduccionDiaraLechePaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable) {
        return produccionDiariaLecheDao.findByFechaProduccionLecheBetween(fechaInicio, fechaFin, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProduccionDiariaLeche> obtenerProduccionDiaraLechePaginadoPorFechaAndIdGanadoHembra(LocalDate fechaInicio, LocalDate fechaFin, Long idGanadoHembra, Pageable pageable) {
        return produccionDiariaLecheDao.findByFechaProduccionLecheBetweenAndGanadoHembra_IdGanadoHembra(fechaInicio, fechaFin, idGanadoHembra, pageable);
    }

    @Override
    @Transactional
    public void guardarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        produccionDiariaLeche.setEstadoProduccionDiariaLeche(true);
        produccionDiariaLeche.setTotalProduccionLeche(produccionDiariaLeche.getProduccionManianaLeche().add(produccionDiariaLeche.getProduccionTardeLeche()));
        produccionDiariaLecheDao.save(produccionDiariaLeche);
    }

    @Override
    @Transactional(readOnly = true)
    public ProduccionDiariaLeche encontrarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        return produccionDiariaLecheDao.findById(produccionDiariaLeche.getIdProduccionDiariaLeche()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduccionDiariaLeche> encontrarTotalProduccionFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return produccionDiariaLecheDao.findByFechaProduccionLecheBetween(fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduccionDiariaLeche> encontrarTotalProduccionFechaAndIdGanadoHembra(LocalDate fechaInicio, LocalDate fechaFin, Long idGanadoHembra) {
        return produccionDiariaLecheDao.findByFechaProduccionLecheBetweenAndGanadoHembra_IdGanadoHembra(fechaInicio, fechaFin, idGanadoHembra);
    }

    @Override
    @Transactional
    public void darDeBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        ProduccionDiariaLeche produccionDiariaLecheExistente = produccionDiariaLecheDao.findById(produccionDiariaLeche.getIdProduccionDiariaLeche()).orElse(null);
        if (produccionDiariaLecheExistente != null) {
            produccionDiariaLecheExistente.setEstadoProduccionDiariaLeche(false);
            produccionDiariaLecheDao.save(produccionDiariaLecheExistente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Becerra> obtenerRelacionMadreBecerra(ProduccionDiariaLeche produccionDiariaLeche) {
        // Obtener la vaca desde la produccion
        GanadoHembra vaca = produccionDiariaLeche.getGanadoHembra();
        // Buscar en la base de datos si existe alguna relacion de alguna baca con alguna becerra
        return becerraDao.findByMadre(vaca);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Becerro> obtenerRelacionMadreBecerro(ProduccionDiariaLeche produccionDiariaLeche) {
        GanadoHembra vaca = produccionDiariaLeche.getGanadoHembra();
        return becerroDao.findByGanadoHembra(vaca);
    }

}
