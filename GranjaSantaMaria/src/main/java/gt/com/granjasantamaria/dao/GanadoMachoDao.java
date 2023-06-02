package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.GanadoMacho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface GanadoMachoDao extends JpaRepository<GanadoMacho, Long> {

    List<GanadoMacho> findByEstadoGanadoMachoIsTrue();

}
