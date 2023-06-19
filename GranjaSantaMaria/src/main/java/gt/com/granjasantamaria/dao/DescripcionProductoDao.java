package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DescripcionProducto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescripcionProductoDao extends JpaRepository<DescripcionProducto, Long> {

    List<DescripcionProducto> findByEstadoDescripcionProductoIsTrue();

    Page<DescripcionProducto> findAllByEstadoDescripcionProductoIsTrue(Pageable pageable);

}
