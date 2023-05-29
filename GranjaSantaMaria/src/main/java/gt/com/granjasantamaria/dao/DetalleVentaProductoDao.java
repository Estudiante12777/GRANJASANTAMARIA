package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleVentaProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface DetalleVentaProductoDao extends JpaRepository<DetalleVentaProducto, Long> {

    List<DetalleVentaProducto> findByEstadoDetalleVentaProductoIsTrue();

}
