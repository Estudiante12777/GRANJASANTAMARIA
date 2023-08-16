package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Becerra;
import gt.com.granjasantamaria.modelo.GanadoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BecerraDao extends JpaRepository<Becerra, Long> {

    List<Becerra> findByEstadoRelacionMadreBecerraIsTrue();

    Page<Becerra> findAllByEstadoRelacionMadreBecerraIsTrue(Pageable pageable);

    Optional<Becerra> findByBecerraIdGanadoHembra(Long idBecerra);

    List<Becerra> findByMadre(GanadoHembra madre);

}
