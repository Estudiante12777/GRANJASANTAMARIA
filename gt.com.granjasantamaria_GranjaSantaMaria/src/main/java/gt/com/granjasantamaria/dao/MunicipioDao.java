package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Municipio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface MunicipioDao extends JpaRepository<Municipio, Long> {

    List<Municipio> findByEstadoMunicipioIsTrue();

}
