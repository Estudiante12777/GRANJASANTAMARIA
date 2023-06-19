package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DescripcionProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DescripcionProductoService {

    public List<DescripcionProducto> obtenerListadoDescripcionProductos();

    public Page<DescripcionProducto> obtenerListadoDescripcionProductoPaginado(Pageable pageable);

    public void guardarDescripcionProducto(DescripcionProducto descripcionProducto);

    public void eliminarDescripcionProducto(DescripcionProducto descripcionProducto);

    public DescripcionProducto encontrarDescripcionProducto(DescripcionProducto descripcionProducto);

    public void darBajaDescripcionProducto(DescripcionProducto descripcionProducto);

}
