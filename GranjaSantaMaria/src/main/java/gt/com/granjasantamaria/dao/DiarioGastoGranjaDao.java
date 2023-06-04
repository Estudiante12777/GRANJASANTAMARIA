package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface DiarioGastoGranjaDao extends JpaRepository<DiarioGastoGranja, Long> {

    List<DiarioGastoGranja> findByEstadoDiarioGastoGranjaIsTrue();

}
