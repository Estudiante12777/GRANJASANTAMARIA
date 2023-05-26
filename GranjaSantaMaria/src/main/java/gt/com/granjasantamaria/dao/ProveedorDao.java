package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface ProveedorDao extends JpaRepository<Proveedor, Long> {
    
    List<Proveedor> findByEstadoProveedorIsTrue(); 
    
}
