package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.InventarioProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface InventarioProductoService {

    List<InventarioProducto> obtenerListadoInventarioProductos();

    Page<InventarioProducto> obtenerListadoInventarioProductoPaginado(Pageable pageable);

    Page<InventarioProducto> obtenerListaTotalInventarioProductoPaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    Page<InventarioProducto> obtenerListaTotalInventarioProductoPaginadoPorFechaAndIdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto, Pageable pageable);

    void guardarInventarioProducto(InventarioProducto inventarioProducto);

    void eliminarInventarioProducto(InventarioProducto inventarioProducto);

    InventarioProducto encontrarInventarioProducto(InventarioProducto inventarioProducto);

    List<InventarioProducto> encontrarTotalInventarioProducto(LocalDate fechaInicio, LocalDate fechaFin);

    List<InventarioProducto> encontrarTotalInventarioProductoAndIdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto);

    void darBajaInventarioProducto(InventarioProducto inventarioProducto);

}
