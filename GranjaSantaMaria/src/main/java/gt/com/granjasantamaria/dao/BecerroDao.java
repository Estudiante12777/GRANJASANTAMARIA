package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Becerro;
import gt.com.granjasantamaria.modelo.GanadoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BecerroDao extends JpaRepository<Becerro, Long> {

    List<Becerro> findByEstadoRelacionMadreBecerroIsTrue();

    Page<Becerro> findAllByEstadoRelacionMadreBecerroIsTrue(Pageable pageable);

    List<Becerro> findByGanadoHembra(GanadoHembra ganadoHembra);

}
