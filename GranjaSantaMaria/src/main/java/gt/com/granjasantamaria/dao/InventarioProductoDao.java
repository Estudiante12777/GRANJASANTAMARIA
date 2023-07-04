package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.InventarioProducto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioProductoDao extends JpaRepository<InventarioProducto, Long> {

    List<InventarioProducto> findByEstadoInventarioProductoIsTrue();

    Page<InventarioProducto> findAllByEstadoInventarioProductoIsTrue(Pageable pageable);

    List<InventarioProducto> findByFechaInventarioProductoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<InventarioProducto> findByFechaInventarioProductoBetweenAndDetalleProducto_IdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto);

    Page<InventarioProducto> findByFechaInventarioProductoBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    Page<InventarioProducto> findByFechaInventarioProductoBetweenAndDetalleProducto_IdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto, Pageable pageable);

}
