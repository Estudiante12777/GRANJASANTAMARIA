package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface ProduccionDiariaLecheDao extends JpaRepository<ProduccionDiariaLeche, Long> {

    List<ProduccionDiariaLeche> findByEstadoProduccionDiariaLecheTrue();
    
    List<ProduccionDiariaLeche> findByFechaProduccionLecheAndEstadoProduccionDiariaLecheTrue(Date fechaProduccionLeche);

}
