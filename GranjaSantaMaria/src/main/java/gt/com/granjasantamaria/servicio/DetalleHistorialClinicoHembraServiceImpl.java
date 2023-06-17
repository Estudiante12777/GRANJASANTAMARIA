package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.DetalleHistorialClinicoHembraDao;
import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoHembra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleHistorialClinicoHembraServiceImpl implements DetalleHistorialClinicoHembraService {

    @Autowired
    private DetalleHistorialClinicoHembraDao detalleHistorialClinicoHembraDao;

    @Override
    @Transactional(readOnly = true)
    public Page<DetalleHistorialClinicoHembra> obtenerListadoDetalleHistorialClinicoHembraPaginado(Long idHistorialClinicoHembra, Pageable pageable) {
        return detalleHistorialClinicoHembraDao.findAllByIdHistorialClinicoHembraAndEstadoDetalleHistorialClinicoHembraIsTrue(idHistorialClinicoHembra, pageable);
    }

    @Override
    public void guardarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        detalleHistorialClinicoHembra.setEstadoDetalleHistorialClinicoHembra(true);
        detalleHistorialClinicoHembraDao.save(detalleHistorialClinicoHembra);
    }

    @Override
    public void eliminarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        detalleHistorialClinicoHembraDao.delete(detalleHistorialClinicoHembra);
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleHistorialClinicoHembra encontrarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        return detalleHistorialClinicoHembraDao.findById(detalleHistorialClinicoHembra.getIdDetalleHistorialClinicoHembra()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        DetalleHistorialClinicoHembra detalleHistorialClinicoHembraExistente = detalleHistorialClinicoHembraDao.findById(detalleHistorialClinicoHembra.getIdDetalleHistorialClinicoHembra()).orElse(null);
        if (detalleHistorialClinicoHembraExistente != null) {
            detalleHistorialClinicoHembraExistente.setEstadoDetalleHistorialClinicoHembra(false);
            detalleHistorialClinicoHembraDao.save(detalleHistorialClinicoHembraExistente);
        }
    }

}
