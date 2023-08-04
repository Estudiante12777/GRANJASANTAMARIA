package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.MedidaProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedidaProductoService {

    List<MedidaProducto> obtenerListadoMedidaProductos();

    Page<MedidaProducto> obtenerListadoMedidaProductoPaginado(Pageable pageable);

    void guardarMedidaProducto(MedidaProducto medidaProducto);

    MedidaProducto encontrarMedidaProducto(MedidaProducto medidaProducto);

    void darBajaMedidaProducto(MedidaProducto medidaProducto);

}
