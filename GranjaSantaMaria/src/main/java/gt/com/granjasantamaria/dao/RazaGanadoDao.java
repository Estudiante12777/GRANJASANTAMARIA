package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.*;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RazaGanadoDao extends JpaRepository<RazaGanado, Long> {

    List<RazaGanado> findByEstadoRazaGandoIsTrue();

    Page<RazaGanado> findAllByEstadoRazaGandoIsTrue(Pageable pageable);

}
