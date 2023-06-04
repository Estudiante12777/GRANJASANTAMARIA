package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface DetalleHistorialClinicoMachoDao extends JpaRepository<DetalleHistorialClinicoMacho, Long> {
    
    List<DetalleHistorialClinicoMacho> findByEstadoDetalleHistorialClinicoMachoIsTrue(); 
    
}
