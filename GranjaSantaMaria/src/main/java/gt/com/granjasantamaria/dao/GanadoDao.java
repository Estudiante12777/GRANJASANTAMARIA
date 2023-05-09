package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Ganado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface GanadoDao extends JpaRepository<Ganado, Long> {

    List<Ganado> findByEstadoGanadoTrue();

}
