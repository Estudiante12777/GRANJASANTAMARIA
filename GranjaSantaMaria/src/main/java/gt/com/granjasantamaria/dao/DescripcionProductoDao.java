package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DescripcionProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface DescripcionProductoDao extends JpaRepository<DescripcionProducto, Long> {

    List<DescripcionProducto> findByEstadoDescripcionProductoIsTrue();

}
