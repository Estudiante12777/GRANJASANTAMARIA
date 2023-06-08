package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.GanadoMacho;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GanadoMachoDao extends JpaRepository<GanadoMacho, Long> {

    List<GanadoMacho> findByEstadoGanadoMachoIsTrue();

    Page<GanadoMacho> findAllByEstadoGanadoMachoIsTrue(Pageable pageable);

}
