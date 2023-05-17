package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Departamento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gerso
 */
public interface DepartamentoDao extends JpaRepository<Departamento, Long> {

    List<Departamento> findByEstadoDepartamentoIsTrue();

}
