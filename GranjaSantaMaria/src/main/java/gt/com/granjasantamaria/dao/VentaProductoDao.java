package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import gt.com.granjasantamaria.modelo.VentaProducto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaProductoDao extends JpaRepository<VentaProducto, Long> {

    List<VentaProducto> findByFechaVentaProductoAndEstadoVentaProductoIsTrue(LocalDate fechaProduccionLeche);

    List<VentaProducto> findByFechaVentaProductoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Page<VentaProducto> findByFechaVentaProductoBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

}
