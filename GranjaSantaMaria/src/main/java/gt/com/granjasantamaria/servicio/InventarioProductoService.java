package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.InventarioProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventarioProductoService {

    public List<InventarioProducto> obtenerListadoInventarioProductos();

    public Page<InventarioProducto> obtenerListadoInventarioProductoPaginado(Pageable pageable);

    public void guardarInventarioProducto(InventarioProducto inventarioProducto);

    public void eliminarInventarioProducto(InventarioProducto inventarioProducto);

    public InventarioProducto encontrarInventarioProducto(InventarioProducto inventarioProducto);

    public void darBajaInventarioProducto(InventarioProducto inventarioProducto);

}
