package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoHembra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface DetalleHistorialClinicoHembraDao extends JpaRepository<DetalleHistorialClinicoHembra, Long> {

    List<DetalleHistorialClinicoHembra> findByEstadoDetalleHistorialClinicoHembraIsTrue();

}
