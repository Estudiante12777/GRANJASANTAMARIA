package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleVentaProducto;
import gt.com.granjasantamaria.modelo.VentaProducto;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface VentaProductoService {

    public List<VentaProducto> obtenerListaVentaProductos();

    public void guardarVentaProducto(VentaProducto ventaProducto, DetalleVentaProducto detalleVentaProducto);

    public void eliminarVentaProducto(VentaProducto ventaProducto);

    public VentaProducto encontrarVentaProducto(VentaProducto ventaProducto);

    public void darBajaVentaProducto(VentaProducto ventaProducto);

}
