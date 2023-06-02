package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface AlimentacionBecerraDao extends JpaRepository<AlimentacionBecerra, Long> {

    List<AlimentacionBecerra> findByEstadoAlimentacionBecerraIsTrue();

}
