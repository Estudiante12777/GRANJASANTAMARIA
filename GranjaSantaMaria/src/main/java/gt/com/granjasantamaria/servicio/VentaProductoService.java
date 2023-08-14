package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.VentaProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface VentaProductoService {

    List<VentaProducto> obtenerListadoVentaProducto();

    List<VentaProducto> obtenerListaTotalVentaProducto();

    Page<VentaProducto> obtenerListaTotalVentaProductoPaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    Page<VentaProducto> obtenerListaTotalVentaProductoPaginadoPorFechaAndIdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto, Pageable pageable);

    void guardarVentaProducto(VentaProducto ventaProducto);

    void editarVentaProducto(VentaProducto ventaProducto);

    VentaProducto encontrarVentaProducto(VentaProducto ventaProducto);

    List<VentaProducto> encontrarTotalVentaProducto(LocalDate fechaInicio, LocalDate fechaFin);

    List<VentaProducto> encontrarTotalVentaProductoAndIdDetalleProducto(LocalDate fechaInicio, LocalDate fechaFin, Long idDetalleProducto);

    void darBajaVentaProducto(VentaProducto ventaProducto);

    BigDecimal obtenerTotalVentas();

}
