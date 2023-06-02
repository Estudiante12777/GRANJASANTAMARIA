package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.MedidaProducto;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface MedidaProductoService {

    public List<MedidaProducto> obtenerListadoMedidaProductos();

    public void guardarMedidaProducto(MedidaProducto medidaProducto);

    public void eliminarMedidaProducto(MedidaProducto medidaProducto);

    public MedidaProducto encontrarMedidaProducto(MedidaProducto medidaProducto);

    public void darBajaMedidaProducto(MedidaProducto medidaProducto);

}
