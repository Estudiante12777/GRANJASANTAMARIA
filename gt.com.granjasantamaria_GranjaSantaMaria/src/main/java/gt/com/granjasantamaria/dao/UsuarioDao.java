package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}
