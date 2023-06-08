package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.GanadoHembra;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GanadoHembraDao extends JpaRepository<GanadoHembra, Long> {

    List<GanadoHembra> findByEstadoGanadoHembraIsTrue();
    Page<GanadoHembra> findAllByEstadoGanadoHembraIsTrue(Pageable pageable);

}
