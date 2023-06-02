package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Producto;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface ProductoService {

    public List<Producto> obtenerListadoProductos();

    public void guardarProducto(Producto producto);

    public void eliminarProducto(Producto producto);

    public Producto encontranProducto(Producto producto);

    public void darBajaProducto(Producto producto);

}
