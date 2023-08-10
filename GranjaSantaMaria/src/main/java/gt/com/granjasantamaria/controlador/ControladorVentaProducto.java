package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.reportes.ReporteVentaProductoFecha;
import gt.com.granjasantamaria.reportes.ReporteVentaProductoFechaExcel;
import gt.com.granjasantamaria.servicio.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorVentaProducto {

    private final ClienteService clienteService;

    private final DetalleProductoService detalleProductoService;

    private final InventarioProductoService inventarioProductoService;

    private final VentaProductoService ventaProductoService;

    @Autowired
    public ControladorVentaProducto(ClienteService clienteService, DetalleProductoService detalleProductoService, InventarioProductoService inventarioProductoService, VentaProductoService ventaProductoService) {
        this.clienteService = clienteService;
        this.detalleProductoService = detalleProductoService;
        this.inventarioProductoService = inventarioProductoService;
        this.ventaProductoService = ventaProductoService;
    }

    @GetMapping("/modulo-venta/venta-producto/lista")
    public String listaVentaProducto(Model model) {
        var listadoVentasProducto = ventaProductoService.obtenerListadoVentaProducto();
        BigDecimal sumaTotalPrecioProducto = listadoVentasProducto.stream().map(VentaProducto::getTotalPrecioProducto).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("listadoVentasProducto", listadoVentasProducto);
        model.addAttribute("sumaTotalPrecioProducto", sumaTotalPrecioProducto);
        return "pages/modulo-venta/venta-producto/venta-producto";
    }

    @GetMapping("/modulo-venta/venta-producto/total-venta-producto-fecha")
    public String obtenerListaVentaProducto(VentaProducto ventaProducto, Model model) {
        var listaTotalVentaProducto = ventaProductoService.obtenerListaTotalVentaProducto();
        model.addAttribute("listaTotalVentaProducto", listaTotalVentaProducto);
        var obtenerListaProductoDetalle = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("obtenerListaProductoDetalle", obtenerListaProductoDetalle);
        return "pages/modulo-venta/venta-producto/total-venta-producto-fecha";
    }

    @GetMapping("/modulo-venta/venta-producto/encontrar-total-venta-producto-fecha")
    public String encontrarTotalVentaProducto(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, @RequestParam(value = "detalleProducto", required = false) Long idDetalleProducto, @RequestParam(defaultValue = "0") int pagina, Model model) {
        int pageSize = 10; // Tamaño de cada página
        PageRequest pageRequest = PageRequest.of(pagina, pageSize);
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        Page<VentaProducto> ventaProductoPage;
        if (Objects.isNull(idDetalleProducto) || idDetalleProducto.equals(Long.valueOf("0"))) {
            ventaProductoPage = ventaProductoService.obtenerListaTotalVentaProductoPaginadoPorFecha(inicio, fin, pageRequest);
        } else {
            ventaProductoPage = ventaProductoService.obtenerListaTotalVentaProductoPaginadoPorFechaAndIdDetalleProducto(inicio, fin, idDetalleProducto, pageRequest);
        }
        List<VentaProducto> totalVentaProductoFecha = ventaProductoPage.getContent(); // Obtener los elementos de la página actual
        BigDecimal sumarTotalPrecioProducto = totalVentaProductoFecha.stream().map(VentaProducto::getTotalPrecioProducto).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalVentaProductoFecha", totalVentaProductoFecha);
        model.addAttribute("ventaProductoPage", ventaProductoPage);
        model.addAttribute("sumarTotalPrecioProducto", sumarTotalPrecioProducto);
        return "pages/modulo-venta/venta-producto/total-venta-producto-fecha";
    }

    private List<VentaProducto> encontrarTotalVentaPorFechaYPorProducto(LocalDate fechaInicio, LocalDate fechaFin, Long ideDetalleProucto) {
        if (Objects.isNull(ideDetalleProucto) || ideDetalleProucto.equals(Long.valueOf("0"))) {
            return ventaProductoService.encontrarTotalVentaProducto(fechaInicio, fechaFin);
        } else {
            return ventaProductoService.encontrarTotalVentaProductoAndIdDetalleProducto(fechaInicio, fechaFin, ideDetalleProucto);
        }
    }

    @GetMapping("/modulo-venta/venta-producto/total-venta-producto-fecha/pdf")
    public ModelAndView generarPDFTotalVentaProducto(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, @RequestParam(value = "detalleProducto", required = false) Long idDetalleProducto) {
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        // Obtener todos los registros de producción de leche para el rango de fechas dado
        List<VentaProducto> totalVentaProductoFecha = encontrarTotalVentaPorFechaYPorProducto(inicio, fin, idDetalleProducto);
        // Obtener el número total de registros
        long totalRegistros = totalVentaProductoFecha.size();
        // Crear el modelo para el PDF
        Map<String, Object> model = new HashMap<>();
        model.put("totalRegistros", totalRegistros);
        model.put("totalVentaProductoFecha", totalVentaProductoFecha);
        // Devolver la vista PDF y el modelo
        return new ModelAndView(new ReporteVentaProductoFecha(), model);
    }

    @GetMapping("/modulo-venta/venta-producto/total-venta-producto-fecha/excel")
    public ModelAndView generarExcelTotalVentaProducto(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, @RequestParam(value = "detalleProducto", required = false) Long idDetalleProducto) {
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        // Obtener todos los registros de producción de leche para el rango de fechas y el ganado hembra dado
        List<VentaProducto> totalVentaProductoFecha = encontrarTotalVentaPorFechaYPorProducto(inicio, fin, idDetalleProducto);
        // Obtener el número total de registros
        long totalRegistros = totalVentaProductoFecha.size();
        // Crear el modelo para el Excel
        Map<String, Object> model = new HashMap<>();
        model.put("totalRegistros", totalRegistros);
        model.put("totalVentaProductoFecha", totalVentaProductoFecha);
        // Devolver la vista Excel y el modelo
        return new ModelAndView(new ReporteVentaProductoFechaExcel(), model);
    }

    @GetMapping("/modulo-venta/venta-producto/existencia/{idInventarioProducto}")
    public ResponseEntity<Integer> obtenerExistenciaProducto(@PathVariable("idInventarioProducto") Long idInventarioProducto) {
        int existencia = inventarioProductoService.obtenerExistenciaProducto(idInventarioProducto);
        return ResponseEntity.ok(existencia);
    }

    @GetMapping("/modulo-venta/venta-producto/agregar")
    public String agregarVentaProducto(VentaProducto ventaProducto, Model model) {
        List<Cliente> listadoClientes = clienteService.listadoClientes();
        model.addAttribute("listadoClientes", listadoClientes);
        List<InventarioProducto> listadoInventarioProductos = inventarioProductoService.obtenerListadoInventarioProductos();
        List<InventarioProducto> productosDisponibles = new ArrayList<>();
        for (InventarioProducto inventarioProducto : listadoInventarioProductos) {
            if (inventarioProducto.getCantidadIngresadaProducto() > inventarioProducto.getCantidadVendidaHastaHoy()) {
                productosDisponibles.add(inventarioProducto);
            }
        }
        model.addAttribute("listadoInventarioProductos", productosDisponibles);
        return "pages/modulo-venta/venta-producto/modificar-venta-producto";
    }

    @PostMapping("/modulo-venta/venta-producto/guardar")
    public String guardarVentaProducto(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) VentaProducto ventaProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            ventaProductoService.guardarVentaProducto(ventaProducto);
            return "redirect:/modulo-venta/venta-producto/lista";
        }
    }

    @GetMapping("/modulo-venta/venta-producto/editar/{idVentaProducto}")
    public String editarVentaProducto(VentaProducto ventaProducto, Model model) {
        List<Cliente> listadoClientes = clienteService.listadoClientes();
        model.addAttribute("listadoClientes", listadoClientes);
        List<InventarioProducto> listadoInventarioProductos = inventarioProductoService.obtenerListadoInventarioProductos();
        model.addAttribute("listadoInventarioProductos", listadoInventarioProductos);
        ventaProducto = ventaProductoService.encontrarVentaProducto(ventaProducto);
        model.addAttribute("ventaProducto", ventaProducto);
        return "pages/modulo-venta/venta-producto/modificar-venta-producto";
    }

    @GetMapping("/modulo-venta/venta-producto/baja")
    public String darBajaVentaProducto(VentaProducto ventaProducto) {
        ventaProductoService.darBajaVentaProducto(ventaProducto);
        return "redirect:/modulo-venta/venta-producto/lista";
    }

}
