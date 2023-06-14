package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.AlimentacionBecerro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

public interface AlimentacionBecerroDao extends JpaRepository<AlimentacionBecerro, Long> {

    Page<AlimentacionBecerro> findAllByEstadoAlimentacionBecerroIsTrue(Pageable pageable);

}
