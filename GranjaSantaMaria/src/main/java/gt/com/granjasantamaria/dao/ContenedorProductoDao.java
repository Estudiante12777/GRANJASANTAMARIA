package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.ContenedorProducto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenedorProductoDao extends JpaRepository<ContenedorProducto, Long> {

    List<ContenedorProducto> findByEstadoContenedorProductoIsTrue();

    Page<ContenedorProducto> findAllByEstadoContenedorProductoIsTrue(Pageable pageable);

}
