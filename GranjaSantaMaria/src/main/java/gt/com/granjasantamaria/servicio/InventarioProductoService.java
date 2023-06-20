package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import gt.com.granjasantamaria.modelo.InventarioProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface InventarioProductoService {

    public List<InventarioProducto> obtenerListadoInventarioProductos();

    public Page<InventarioProducto> obtenerListadoInventarioProductoPaginado(Pageable pageable);

    Page<InventarioProducto> obtenerListaTotalInventarioProductoPaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    public void guardarInventarioProducto(InventarioProducto inventarioProducto);

    public void eliminarInventarioProducto(InventarioProducto inventarioProducto);

    public InventarioProducto encontrarInventarioProducto(InventarioProducto inventarioProducto);

    List<InventarioProducto> encontrarTotalInventarioProducto(LocalDate fechaInicio, LocalDate fechaFin);

    public void darBajaInventarioProducto(InventarioProducto inventarioProducto);

}
