package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.InventarioProducto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioProductoDao extends JpaRepository<InventarioProducto, Long> {

    List<InventarioProducto> findByEstadoInventarioProductoIsTrue();

    Page<InventarioProducto> findAllByEstadoInventarioProductoIsTrue(Pageable pageable);

    public InventarioProducto findByDetalleProductoAndEstadoInventarioProductoIsTrue(DetalleProducto detalleProducto);

}
