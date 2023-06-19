package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Producto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Long> {

    List<Producto> findByEstadoProductoIsTrue();

    Page<Producto> findAllByEstadoProductoIsTrue(Pageable pageable);

}
