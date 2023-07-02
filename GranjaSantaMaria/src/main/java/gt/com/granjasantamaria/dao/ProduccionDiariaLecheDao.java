package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;

import java.time.LocalDate;
import java.util.List;

import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduccionDiariaLecheDao extends JpaRepository<ProduccionDiariaLeche, Long> {

    List<ProduccionDiariaLeche> findByFechaProduccionLecheAndEstadoProduccionDiariaLecheIsTrue(LocalDate fechaProduccionLeche);

    List<ProduccionDiariaLeche> findByFechaProduccionLecheBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Page<ProduccionDiariaLeche> findByFechaProduccionLecheBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    Page<ProduccionDiariaLeche> findByFechaProduccionLecheBetweenAndGanadoHembra_IdGanadoHembra(LocalDate fechaInicio, LocalDate fechaFin, Long idGanadoHembra, Pageable pageable);

    List<ProduccionDiariaLeche> findByFechaProduccionLecheBetweenAndGanadoHembra_IdGanadoHembra(LocalDate fechaInicio, LocalDate fechaFin, Long idGanadoHembra);

}
