package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleVentaProducto;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface DetalleVentaProductoService {

    public List<DetalleVentaProducto> obtenerListadoDetalleVentaProductos();

    public void guardarDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto);

    public void eliminarDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto);

    public DetalleVentaProducto encontrarDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto);

    public void darBajaDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto);

}
