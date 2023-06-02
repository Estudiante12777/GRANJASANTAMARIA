package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface GanadoHembraDao extends JpaRepository<GanadoHembra, Long> {

    List<GanadoHembra> findByEstadoGanadoHembraIsTrue();

}
