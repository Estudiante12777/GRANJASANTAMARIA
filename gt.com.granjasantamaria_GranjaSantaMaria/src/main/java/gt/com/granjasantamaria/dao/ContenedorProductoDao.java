package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.ContenedorProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface ContenedorProductoDao extends JpaRepository<ContenedorProducto, Long> {

    List<ContenedorProducto> findByEstadoContenedorProductoIsTrue();

}
