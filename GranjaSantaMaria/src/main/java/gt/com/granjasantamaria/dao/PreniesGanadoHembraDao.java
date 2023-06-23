package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import gt.com.granjasantamaria.modelo.PreniesGanadoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreniesGanadoHembraDao extends JpaRepository<PreniesGanadoHembra, Long> {

    Page<PreniesGanadoHembra> findAllByEstadoPreniesGanadoHembraIsTrue(Pageable pageable);

    List<PreniesGanadoHembra> findByEstadoPreniesGanadoHembraIsTrue();

}
