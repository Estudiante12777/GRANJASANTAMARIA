package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {

    public List<Producto> obtenerListadoProductos();

    public Page<Producto> obtenerListadoProductoPaginado(Pageable pageable);

    public void guardarProducto(Producto producto);

    public void eliminarProducto(Producto producto);

    public Producto encontranProducto(Producto producto);

    public void darBajaProducto(Producto producto);

}
