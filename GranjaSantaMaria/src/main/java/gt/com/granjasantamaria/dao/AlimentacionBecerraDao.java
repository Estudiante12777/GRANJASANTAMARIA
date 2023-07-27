package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentacionBecerraDao extends JpaRepository<AlimentacionBecerra, Long> {

    Page<AlimentacionBecerra> findAllByEstadoAlimentacionBecerraIsTrue(Pageable pageable);

}
