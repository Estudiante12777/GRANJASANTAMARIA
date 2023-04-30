package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.CategoriaGanado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface CategoriaGanadoDao extends JpaRepository<CategoriaGanado, Long>{
    
    List<CategoriaGanado> findByEstadoCategoriaGanadoTrue(); 
    
}
