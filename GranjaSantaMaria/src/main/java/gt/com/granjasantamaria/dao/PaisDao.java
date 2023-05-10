package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Pais;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface PaisDao extends JpaRepository<Pais, Long>{
    
    List<Pais> findByEstadoPaisTrue(); 
    
}
