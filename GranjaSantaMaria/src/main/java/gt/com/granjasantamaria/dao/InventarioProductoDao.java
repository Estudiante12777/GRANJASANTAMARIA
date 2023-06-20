package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.InventarioProducto;

import java.time.LocalDate;
import java.util.List;

import gt.com.granjasantamaria.modelo.VentaProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioProductoDao extends JpaRepository<InventarioProducto, Long> {

    List<InventarioProducto> findByEstadoInventarioProductoIsTrue();

    Page<InventarioProducto> findAllByEstadoInventarioProductoIsTrue(Pageable pageable);

    List<InventarioProducto> findByFechaInventarioProductoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Page<InventarioProducto> findByFechaInventarioProductoBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

}
