package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.VentaProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VentaProductoService {

    public List<VentaProducto> obtenerListadoVentaProductos();

    public Page<VentaProducto> obtenerListadoVentaProductoPaginado(Pageable pageable);

    public void guardarVentaProducto(VentaProducto ventaProducto);

    public void eliminarVentaProducto(VentaProducto ventaProducto);

    public VentaProducto encontrarVentaProducto(VentaProducto ventaProducto);

    public void darBajaVentaProducto(VentaProducto ventaProducto);

}
