package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.CategoriaGanado;
import gt.com.granjasantamaria.modelo.Ganado;
import gt.com.granjasantamaria.modelo.TipoGanado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface GanadoDao extends JpaRepository<Ganado, Long> {

    List<Ganado> findByEstadoGanadoTrue();

    List<Ganado> findByTipoGanadoAndEstadoGanadoTrue(TipoGanado tipoGanado);

    List<Ganado> findByCategoriaGanadoAndEstadoGanadoTrue(CategoriaGanado categoriaGanado);

    List<Ganado> findByTipoGanadoAndCategoriaGanadoAndEstadoGanadoTrue(TipoGanado tipoGanado, CategoriaGanado categoriaGanado);

}
