package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleHistorialClinicoMachoDao extends JpaRepository<DetalleHistorialClinicoMacho, Long> {

    Page<DetalleHistorialClinicoMacho> findAllByIdHistorialClinicoMachoAndEstadoDetalleHistorialClinicoMachoIsTrue(Long idHistorialClinicoMacho, Pageable pageable);

}
