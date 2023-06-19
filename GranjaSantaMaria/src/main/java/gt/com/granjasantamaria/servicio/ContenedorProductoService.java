package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.ContenedorProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContenedorProductoService {

    public List<ContenedorProducto> obtenerListadoContenedorProductos();

    public Page<ContenedorProducto> obtenerListadoContenedorProductoPaginado(Pageable pageable);

    public void guardarContenedorProducto(ContenedorProducto contenedorProducto);

    public void eliminarContenedorProducto(ContenedorProducto contenedorProducto);

    public ContenedorProducto encontrarContenedorProducto(ContenedorProducto contenedorProducto);

    public void darBajaContenedorProducto(ContenedorProducto contenedorProducto);

}
