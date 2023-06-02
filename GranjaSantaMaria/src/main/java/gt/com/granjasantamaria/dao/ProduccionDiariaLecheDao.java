package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author gerso
 */
public interface ProduccionDiariaLecheDao extends JpaRepository<ProduccionDiariaLeche, Long> {

    List<ProduccionDiariaLeche> findByEstadoProduccionDiariaLecheIsTrue();

    List<ProduccionDiariaLeche> findByFechaProduccionLecheAndEstadoProduccionDiariaLecheIsTrue(LocalDate fechaProduccionLeche);

    List<ProduccionDiariaLeche> findByFechaProduccionLecheBetween(LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT pdl FROM ProduccionDiariaLeche pdl INNER JOIN pdl.ganadoHembra gh WHERE gh.tipoGanado IN ('novilla', 'vaca')")
    List<ProduccionDiariaLeche> findProduccionDiariaLecheByTipoGanado();

}
