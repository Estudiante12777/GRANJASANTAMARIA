package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoHembra;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleHistorialClinicoHembraDao extends JpaRepository<DetalleHistorialClinicoHembra, Long> {

    Page<DetalleHistorialClinicoHembra> findAllByEstadoDetalleHistorialClinicoHembraIsTrue(Pageable pageable);

}
