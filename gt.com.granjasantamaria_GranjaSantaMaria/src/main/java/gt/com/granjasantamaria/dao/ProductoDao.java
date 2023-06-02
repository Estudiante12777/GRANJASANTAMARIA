package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface ProductoDao extends JpaRepository<Producto, Long> {
    
    List<Producto> findByEstadoProductoIsTrue(); 
    
}
