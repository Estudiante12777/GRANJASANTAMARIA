package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DescripcionProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DescripcionProductoService {

    List<DescripcionProducto> obtenerListadoDescripcionProductos();

    Page<DescripcionProducto> obtenerListadoDescripcionProductoPaginado(Pageable pageable);

    void guardarDescripcionProducto(DescripcionProducto descripcionProducto);

    void eliminarDescripcionProducto(DescripcionProducto descripcionProducto);

    DescripcionProducto encontrarDescripcionProducto(DescripcionProducto descripcionProducto);

    void darBajaDescripcionProducto(DescripcionProducto descripcionProducto);

}
