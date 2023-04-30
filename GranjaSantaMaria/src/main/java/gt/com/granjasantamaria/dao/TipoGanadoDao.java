package gt.com.granjasantamaria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gt.com.granjasantamaria.modelo.TipoGanado;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface TipoGanadoDao extends JpaRepository<TipoGanado, Long> {

    List<TipoGanado> findByEstadoTipoGanadoTrue();

}
