package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.HistorialClinicoMacho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialClinicoMachoDao extends JpaRepository<HistorialClinicoMacho, Long> {

    List<HistorialClinicoMacho> findByEstadoHistorialClinicioMachoIsTrue();

}
