package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.ContenedorProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContenedorProductoService {

    List<ContenedorProducto> obtenerListadoContenedorProductos();

    Page<ContenedorProducto> obtenerListadoContenedorProductoPaginado(Pageable pageable);

    void guardarContenedorProducto(ContenedorProducto contenedorProducto);

    void eliminarContenedorProducto(ContenedorProducto contenedorProducto);

    ContenedorProducto encontrarContenedorProducto(ContenedorProducto contenedorProducto);

    void darBajaContenedorProducto(ContenedorProducto contenedorProducto);

}
