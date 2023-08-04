package gt.com.granjasantamaria.servicio;

import java.util.*;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetalleProductoService {

    List<DetalleProducto> obtenerListadoDetalleProductos();

    Page<DetalleProducto> obtenerListadoDetalleProductoPaginado(Pageable pageable);

    void guardarDetalleProducto(DetalleProducto detalleProducto);

    DetalleProducto encontrarDetalleProducto(DetalleProducto detalleProducto);

    void darBajaDetalleProducto(DetalleProducto detalleProducto);

}
