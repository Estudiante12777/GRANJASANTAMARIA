package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Cliente;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

    List<Cliente> findByEstadoClienteIsTrue();

    Page<Cliente> findAllByEstadoClienteIsTrue(Pageable pageable);

}
