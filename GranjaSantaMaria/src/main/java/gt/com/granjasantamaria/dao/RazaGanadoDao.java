package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface RazaGanadoDao extends JpaRepository<RazaGanado, Long> {

    List<RazaGanado> findByEstadoRazaGandoIsTrue();

}
