package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {

    List<Producto> obtenerListadoProductos();

    Page<Producto> obtenerListadoProductoPaginado(Pageable pageable);

    void guardarProducto(Producto producto);

    Producto encontranProducto(Producto producto);

    void darBajaProducto(Producto producto);

}
