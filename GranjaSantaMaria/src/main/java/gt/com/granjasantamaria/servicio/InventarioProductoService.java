package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.InventarioProducto;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface InventarioProductoService {

    public List<InventarioProducto> obtenerListadoInventarioProductos();

    public void guardarInventarioProducto(InventarioProducto inventarioProducto);

    public void eliminarInventarioProducto(InventarioProducto inventarioProducto);

    public InventarioProducto encontrarInventarioProducto(InventarioProducto inventarioProducto);

    public void darBajaInventarioProducto(InventarioProducto inventarioProducto);

}
