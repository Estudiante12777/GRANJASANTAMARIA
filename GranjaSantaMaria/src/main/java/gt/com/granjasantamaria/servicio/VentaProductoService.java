package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import gt.com.granjasantamaria.modelo.VentaProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface VentaProductoService {

    public List<VentaProducto> obtenerListadoVentaProducto();

    List<VentaProducto> obtenerListaTotalVentaProducto();

    Page<VentaProducto> obtenerListaTotalVentaProductoPaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    public void guardarVentaProducto(VentaProducto ventaProducto);

    public void eliminarVentaProducto(VentaProducto ventaProducto);

    public VentaProducto encontrarVentaProducto(VentaProducto ventaProducto);

    List<VentaProducto> encontrarTotalVentaProducto(LocalDate fechaInicio, LocalDate fechaFin);

    public void darBajaVentaProducto(VentaProducto ventaProducto);

}
