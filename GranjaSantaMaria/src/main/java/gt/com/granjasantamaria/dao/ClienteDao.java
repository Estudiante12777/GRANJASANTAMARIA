package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface ClienteDao extends JpaRepository<Cliente, Long> {

    List<Cliente> findByEstadoClienteTrue();

}
