package gt.com.granjasantamaria.servicio;

import java.util.*;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetalleProductoService {

    public List<DetalleProducto> obtenerListadoDetalleProductos();

    public Page<DetalleProducto> obtenerListadoDetalleProductoPaginado(Pageable pageable);

    public void guardarDetalleProducto(DetalleProducto detalleProducto);

    public void eliminarDetalleProducto(DetalleProducto detalleProducto);

    public DetalleProducto encontrarDetalleProducto(DetalleProducto detalleProducto);

    public void darBajaDetalleProducto(DetalleProducto detalleProducto);

}
