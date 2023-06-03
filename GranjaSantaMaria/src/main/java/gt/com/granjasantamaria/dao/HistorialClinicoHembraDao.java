package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.HistorialClinicoHembra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface HistorialClinicoHembraDao extends JpaRepository<HistorialClinicoHembra, Long> {

    List<HistorialClinicoHembra> findByEstadoHistorialClinicoHembraIsTrue();

}
