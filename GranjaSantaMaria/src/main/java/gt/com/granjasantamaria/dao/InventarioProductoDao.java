package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.InventarioProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface InventarioProductoDao extends JpaRepository<InventarioProducto, Long> {

    List<InventarioProducto> findByEstadoInventarioProductoIsTrue();

    public InventarioProducto findByDetalleProductoAndEstadoInventarioProductoIsTrue(DetalleProducto detalleProducto);

    public InventarioProducto findByDetalleProducto(DetalleProducto detalleProducto);

}