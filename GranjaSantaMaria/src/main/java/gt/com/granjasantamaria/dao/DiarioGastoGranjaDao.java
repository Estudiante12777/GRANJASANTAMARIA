package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;

import java.time.LocalDate;
import java.util.List;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiarioGastoGranjaDao extends JpaRepository<DiarioGastoGranja, Long> {

    List<DiarioGastoGranja> findByFechaGastoAndEstadoDiarioGastoGranjaIsTrue(LocalDate fechaProduccionLeche);

    List<DiarioGastoGranja> findByFechaGastoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Page<DiarioGastoGranja> findByFechaGastoBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

}
