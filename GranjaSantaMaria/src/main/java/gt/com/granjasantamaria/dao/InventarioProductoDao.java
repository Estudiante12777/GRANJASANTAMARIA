package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.InventarioProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface InventarioProductoDao extends JpaRepository<InventarioProducto, Long> {

    List<InventarioProducto> findByEstadoInventarioProductoIsTrue();

}
