package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.MedidaProducto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedidaProductoDao extends JpaRepository<MedidaProducto, Long> {

    List<MedidaProducto> findByEstadoMedidaProductoIsTrue();

    Page<MedidaProducto> findAllByEstadoMedidaProductoIsTrue(Pageable pageable);

}
