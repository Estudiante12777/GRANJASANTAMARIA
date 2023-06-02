package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.AlimentacionBecerro;
import java.util.List;
import org.springframework.data.jpa.repository.*;

/**
 * ...
 */
public interface AlimentacionBecerroDao extends JpaRepository<AlimentacionBecerro, Long> {

    List<AlimentacionBecerro> findByEstadoAlimentacionBecerroIsTrue();

}
