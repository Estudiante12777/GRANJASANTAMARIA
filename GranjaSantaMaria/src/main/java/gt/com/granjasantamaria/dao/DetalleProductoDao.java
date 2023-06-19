package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DetalleProducto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleProductoDao extends JpaRepository<DetalleProducto, Long> {

    List<DetalleProducto> findByEstadoDetalleProductoIsTrue();

    Page<DetalleProducto> findAllByEstadoDetalleProductoIsTrue(Pageable pageable);

}
