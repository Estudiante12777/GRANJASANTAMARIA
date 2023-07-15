package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DiarioGastoGranjaDao;
import gt.com.granjasantamaria.modelo.DiarioGastoGranja;

import java.time.LocalDate;
import java.util.List;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiarioGastoGranjaServiceImpl implements DiarioGastoGranjaService {

    @Autowired
    private DiarioGastoGranjaDao diarioGastoGranjaDao;

    @Override
    @Transactional(readOnly = true)
    public List<DiarioGastoGranja> obtenerListadoDiarioGastosGranja() {
        LocalDate fechaActual = LocalDate.now();
        return diarioGastoGranjaDao.findByFechaGastoAndEstadoDiarioGastoGranjaIsTrue(fechaActual);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiarioGastoGranja> obtenerListaTotalDiarioGastoGranja() {
        LocalDate fechaActual = LocalDate.now();
        return diarioGastoGranjaDao.findByFechaGastoAndEstadoDiarioGastoGranjaIsTrue(fechaActual);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DiarioGastoGranja> obtenerListaTotalDiarioGastoGranjaPaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable) {
        return diarioGastoGranjaDao.findByFechaGastoBetween(fechaInicio, fechaFin, pageable);
    }

    @Override
    @Transactional
    public void guardarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        diarioGastoGranja.setEstadoDiarioGastoGranja(true);
        diarioGastoGranjaDao.save(diarioGastoGranja);
    }

    @Override
    @Transactional
    public void eliminarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        diarioGastoGranjaDao.delete(diarioGastoGranja);
    }

    @Override
    @Transactional(readOnly = true)
    public DiarioGastoGranja encontrarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        return diarioGastoGranjaDao.findById(diarioGastoGranja.getIdDiarioGastoGranja()).orElse(null);
    }

    @Override
    public List<DiarioGastoGranja> encontrarTotalDiarioGastoGranja(LocalDate fechaInicio, LocalDate fechaFin) {
        return diarioGastoGranjaDao.findByFechaGastoBetween(fechaInicio, fechaFin);
    }

    @Override
    @Transactional
    public void darBajaDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        DiarioGastoGranja diarioGastoGranjaExistente = diarioGastoGranjaDao.findById(diarioGastoGranja.getIdDiarioGastoGranja()).orElse(null);
        if (diarioGastoGranjaExistente != null) {
            diarioGastoGranjaExistente.setEstadoDiarioGastoGranja(false);
            diarioGastoGranjaDao.save(diarioGastoGranjaExistente);
        }
    }

    @Override
    public Double obtenerTotalGasto() {
        return diarioGastoGranjaDao.obtenerTotalVentas();
    }

}
