package gt.com.granjasantamaria.servicio;
import java.util.*;

import gt.com.granjasantamaria.modelo.DetalleProducto;

/**
 *
 * @author gerso
 */
public interface DetalleProductoService {

    public List<DetalleProducto> obtenerListadoDetalleProductos();

    public void guardarDetalleProducto(DetalleProducto detalleProducto);

    public void eliminarDetalleProducto(DetalleProducto detalleProducto);

    public DetalleProducto encontrarDetalleProducto(DetalleProducto detalleProducto);

    public void darBajaDetalleProducto(DetalleProducto detalleProducto);
    
}
