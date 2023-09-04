package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.AlimentacionBecerraDao;
import gt.com.granjasantamaria.modelo.AlimentacionBecerra;

import gt.com.granjasantamaria.modelo.Becerra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class AlimentacionBecerraServiceImpl implements AlimentacionBecerraService {

    private final AlimentacionBecerraDao alimentacionBecerraDao;

    private final EntityManager entityManager;

    @Autowired
    public AlimentacionBecerraServiceImpl(AlimentacionBecerraDao alimentacionBecerraDao, EntityManager entityManager) {
        this.alimentacionBecerraDao = alimentacionBecerraDao;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Object[]> obtenerAlimentacionBecerraPaginado(Pageable pageable) {
        return alimentacionBecerraDao.obtenerDatosAlimentacionConRelaciones(pageable);
    }

    @Override
    @Transactional
    public void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        alimentacionBecerra.setEstadoAlimentacionBecerra(true);
        alimentacionBecerraDao.save(alimentacionBecerra);
    }

    @Override
    @Transactional(readOnly = true)
    public AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        return alimentacionBecerraDao.findById(alimentacionBecerra.getIdAlimentacionBecerra()).orElse(null);
    }

    @Override
    @Transactional
    public void darDeBajaAlimentacionBecerra(Long idAlimentacionBecerra) {
        alimentacionBecerraDao.darDeBajaPorId(idAlimentacionBecerra);
        entityManager.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public AlimentacionBecerra encontrarAlimentacionBecerraPorId(Long idAlimentacionBecerra) {
        String jpql = "SELECT NEW AlimentacionBecerra(a.idAlimentacionBecerra, a.fechaAlimentacionBecerra, a.cantidadManianaAlimentacion, a.cantidadTardeAlimentacion, a.totalAlimentacionBecerra, a.estadoAlimentacionBecerra, b) " +
                "FROM AlimentacionBecerra a " +
                "LEFT JOIN a.becerra b " +
                "WHERE a.idAlimentacionBecerra = :idAlimentacionBecerra";
        System.out.println("SQL: " + jpql);
        TypedQuery<AlimentacionBecerra> query = entityManager.createQuery(jpql, AlimentacionBecerra.class);
        query.setParameter("idAlimentacionBecerra", idAlimentacionBecerra);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String encontrarNombreBecerraPorIdAlimentacionBecerra(Long idAlimentacionBecerra) {
        return alimentacionBecerraDao.findNombreBecerraByIdAlimentacionBecerra(idAlimentacionBecerra);
    }

}
