package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.MedidaProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface MedidaProductoDao extends JpaRepository<MedidaProducto, Long> {

    List<MedidaProducto> findByEstadoMedidaProductoIsTrue();

}
