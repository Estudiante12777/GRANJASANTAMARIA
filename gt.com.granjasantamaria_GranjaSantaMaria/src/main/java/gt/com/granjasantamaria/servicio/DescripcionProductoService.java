package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DescripcionProducto;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface DescripcionProductoService {

    public List<DescripcionProducto> obtenerListadoDescripcionProductos();

    public void guardarDescripcionProducto(DescripcionProducto descripcionProducto);

    public void eliminarDescripcionProducto(DescripcionProducto descripcionProducto);

    public DescripcionProducto encontrarDescripcionProducto(DescripcionProducto descripcionProducto);

    public void darBajaDescripcionProducto(DescripcionProducto descripcionProducto);
    
}
