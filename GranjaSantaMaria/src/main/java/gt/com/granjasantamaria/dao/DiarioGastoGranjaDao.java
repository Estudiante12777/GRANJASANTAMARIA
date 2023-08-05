package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiarioGastoGranjaDao extends JpaRepository<DiarioGastoGranja, Long> {

    List<DiarioGastoGranja> findByFechaGastoAndEstadoDiarioGastoGranjaIsTrue(LocalDate fechaProduccionLeche);

    List<DiarioGastoGranja> findByFechaGastoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Page<DiarioGastoGranja> findByFechaGastoBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    @Query("SELECT SUM(d.valorTotal) FROM DiarioGastoGranja d WHERE d.fechaGasto = CURRENT_DATE")
    BigDecimal obtenerTotalVentas();

}
