package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.ContenedorProducto;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface ContenedorProductoService {

    public List<ContenedorProducto> obtenerListadoContenedorProductos();

    public void guardarContenedorProducto(ContenedorProducto contenedorProducto);

    public void eliminarContenedorProducto(ContenedorProducto contenedorProducto);

    public ContenedorProducto encontrarContenedorProducto(ContenedorProducto contenedorProducto);

    public void darBajaContenedorProducto(ContenedorProducto contenedorProducto);

}
