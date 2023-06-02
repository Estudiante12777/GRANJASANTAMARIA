package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.VentaProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface VentaProductoDao extends JpaRepository<VentaProducto, Long> {
    
    List<VentaProducto> findByEstadoVentaProductoIsTrue(); 
    
}
