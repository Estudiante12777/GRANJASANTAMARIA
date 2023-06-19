package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.VentaProducto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaProductoDao extends JpaRepository<VentaProducto, Long> {

    List<VentaProducto> findByEstadoVentaProductoIsTrue();

    Page<VentaProducto> findAllByEstadoVentaProductoIsTrue(Pageable pageable);

}
