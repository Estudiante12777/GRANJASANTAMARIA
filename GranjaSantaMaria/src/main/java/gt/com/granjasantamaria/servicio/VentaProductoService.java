package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.VentaProducto;
import java.util.List;

/**
 * Servicio para gestionar las ventas de productos.
 */
public interface VentaProductoService {

    public List<VentaProducto> obtenerListadoVentaProductos();

    public void guardarVentaProducto(VentaProducto ventaProducto);

    public void eliminarVentaProducto(VentaProducto ventaProducto);

    public VentaProducto encontrarVentaProducto(VentaProducto ventaProducto);

    public void darBajaVentaProducto(VentaProducto ventaProducto);

}
