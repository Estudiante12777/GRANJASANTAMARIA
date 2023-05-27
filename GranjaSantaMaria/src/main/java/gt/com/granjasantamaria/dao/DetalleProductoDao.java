package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface DetalleProductoDao extends JpaRepository<DetalleProducto, Long> {

    List<DetalleProducto> findByEstadoDetalleProductoIsTrue();

}
