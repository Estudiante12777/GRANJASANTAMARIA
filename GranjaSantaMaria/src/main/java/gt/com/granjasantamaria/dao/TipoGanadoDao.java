package gt.com.granjasantamaria.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import gt.com.granjasantamaria.modelo.TipoGanado;

import java.util.List;

public interface TipoGanadoDao extends JpaRepository<TipoGanado, Long> {

    List<TipoGanado> findByEstadoTipoGanadoIsTrue();

    Page<TipoGanado> findAllByEstadoTipoGanadoIsTrue(Pageable pageable);

}
