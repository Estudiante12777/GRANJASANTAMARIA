package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.InventarioProducto;
import gt.com.granjasantamaria.reportes.ReporteInventarioProductoFecha;
import gt.com.granjasantamaria.reportes.ReporteInventarioProductoFechaExcel;
import gt.com.granjasantamaria.reportes.ReporteProduccionLecheFecha;
import gt.com.granjasantamaria.servicio.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorInventarioProducto {

    @Autowired
    private InventarioProductoService inventarioProductoService;

    @Autowired
    private DetalleProductoService detalleProductoService;

    @GetMapping("/modulo-inventario/inventario-producto/lista")
    public String obtenerListadoInventarioProductos(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<InventarioProducto> inventarioProductoPage = inventarioProductoService.obtenerListadoInventarioProductoPaginado(pageRequest);
        model.addAttribute("inventarioProductoPage", inventarioProductoPage);
        List<InventarioProducto> inventarioProductos = inventarioProductoPage.getContent().stream().filter(inventarioProducto -> inventarioProducto.getCantidadIngresadaProducto() > inventarioProducto.getCantidadVendidaHastaHoy()).limit(10).collect(Collectors.toList());
        model.addAttribute("inventarioProductos", inventarioProductos);
        return "pages/modulo-inventario/inventario-producto/inventario-producto";
    }

    @GetMapping("/modulo-inventario/inventario-producto/total-inventario-producto-fecha")
    public String obtenerListaDiarioGastoGranja(InventarioProducto inventarioProducto, Model model) {
        var listaTotalInventarioProducto = inventarioProductoService.obtenerListadoInventarioProductos();
        model.addAttribute("listaTotalInventarioProducto", listaTotalInventarioProducto);
        var obtenerListaProductoDetallado = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("obtenerListaProductoDetallado", obtenerListaProductoDetallado);
        return "pages/modulo-inventario/inventario-producto/total-inventario-producto-fecha";
    }

    @GetMapping("/modulo-inventario/inventario-producto/encontrar-total-inventario-producto-fecha")
    public String encontrarTotalDiarioGastoGranja(@RequestParam("fechaInicio") String fechaInicio,
                                                  @RequestParam("fechaFin") String fechaFin,
                                                  @RequestParam(value = "detalleProducto", required = false) Long idDetalleProducto,
                                                  @RequestParam(defaultValue = "0") int pagina, Model model) {
        int pageSize = 10; // Tamaño de cada página
        PageRequest pageRequest = PageRequest.of(pagina, pageSize);
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        Page<InventarioProducto> inventarioProductoPage;
        // Valida si se filtra por algun producto especifico o por todos.
        if (Objects.isNull(idDetalleProducto) || idDetalleProducto.equals(Long.valueOf("0"))) {
            inventarioProductoPage = inventarioProductoService.obtenerListaTotalInventarioProductoPaginadoPorFecha(inicio, fin, pageRequest);
        } else {
            inventarioProductoPage = inventarioProductoService.obtenerListaTotalInventarioProductoPaginadoPorFechaAndIdDetalleProducto(inicio, fin, idDetalleProducto, pageRequest);
        }
        List<InventarioProducto> totalInventarioProductoFecha = inventarioProductoPage.getContent(); // Obtener los elementos de la página actual
        model.addAttribute("totalInventarioProductoFecha", totalInventarioProductoFecha);
        model.addAttribute("inventarioProductoPage", inventarioProductoPage);
        return "pages/modulo-inventario/inventario-producto/total-inventario-producto-fecha";
    }

    @GetMapping("/modulo-inventario/inventario-producto/total-inventario-producto-fecha/pdf")
    public ModelAndView generarPDFTotalInventarioProducto(@RequestParam("fechaInicio") String fechaInicio,
                                                          @RequestParam("fechaFin") String fechaFin,
                                                          @RequestParam(value = "detalleProducto", required = false) Long idDetalleProducto) {
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        // Obtener todos los registros de producción de leche para el rango de fechas dado
        List<InventarioProducto> totalInventarioProductoFecha;
        // Valida si se genera el reporte por producto o por productos
        if (Objects.isNull(idDetalleProducto) || idDetalleProducto.equals(Long.valueOf("0"))) {
            totalInventarioProductoFecha = inventarioProductoService.encontrarTotalInventarioProducto(inicio, fin);
        } else {
            totalInventarioProductoFecha = inventarioProductoService.encontrarTotalInventarioProductoAndIdDetalleProducto(inicio, fin, idDetalleProducto);
        }
        // Obtener el número total de registros
        long totalRegistros = totalInventarioProductoFecha.size();
        // Crear el modelo para el PDF
        Map<String, Object> model = new HashMap<>();
        model.put("totalRegistros", totalRegistros);
        model.put("totalInventarioProductoFecha", totalInventarioProductoFecha);
        // Devolver la vista PDF y el modelo
        return new ModelAndView(new ReporteInventarioProductoFecha(), model);
    }

    @GetMapping("/modulo-inventario/inventario-producto/total-inventario-producto-fecha/excel")
    public ModelAndView generarExcelTotalInventarioProducto(@RequestParam("fechaInicio") String fechaInicio,
                                                            @RequestParam("fechaFin") String fechaFin,
                                                            @RequestParam(value = "detalleProducto", required = false) Long idDetalleProducto) {
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        // Obtener todos los registros de producción de leche para el rango de fechas dado
        List<InventarioProducto> totalInventarioProductoFecha;
        // Valida si se genera el reporte por producto o por productos
        if (Objects.isNull(idDetalleProducto) || idDetalleProducto.equals(Long.valueOf("0"))) {
            totalInventarioProductoFecha = inventarioProductoService.encontrarTotalInventarioProducto(inicio, fin);
        } else {
            totalInventarioProductoFecha = inventarioProductoService.encontrarTotalInventarioProductoAndIdDetalleProducto(inicio, fin, idDetalleProducto);
        }
        // Obtener el número total de registros
        long totalRegistros = totalInventarioProductoFecha.size();
        // Crear el modelo para el PDF
        Map<String, Object> model = new HashMap<>();
        model.put("totalRegistros", totalRegistros);
        model.put("totalInventarioProductoFecha", totalInventarioProductoFecha);
        // Devolver la vista PDF y el modelo
        return new ModelAndView(new ReporteInventarioProductoFechaExcel(), model);
    }

    @GetMapping("/modulo-inventario/inventario-producto/agregar")
    public String agregarInventarioProducto(InventarioProducto inventarioProducto, Model model) {
        List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listaDetalleProductos", listaDetalleProductos);
        return "pages/modulo-inventario/inventario-producto/modificar-inventario-producto";
    }

    @PostMapping("/modulo-inventario/inventario-producto/guardar")
    public String guardarInventarioProducto(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) InventarioProducto inventarioProducto,
                                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pages/modulo-inventario/inventario-producto/modificar-inventario-producto";
        } else {
            List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
            model.addAttribute("listaDetalleProductos", listaDetalleProductos);
            inventarioProductoService.guardarInventarioProducto(inventarioProducto);
            model.addAttribute("listaDetalleProductos", listaDetalleProductos);
            return "redirect:/modulo-inventario/inventario-producto/lista";
        }
    }

    @GetMapping("/modulo-inventario/inventario-producto/editar/{idInventarioProducto}")
    public String editarInventarioProducto(InventarioProducto inventarioProducto, Model model) {
        List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listaDetalleProductos", listaDetalleProductos);
        inventarioProducto = inventarioProductoService.encontrarInventarioProducto(inventarioProducto);
        model.addAttribute("inventarioProducto", inventarioProducto);
        return "pages/modulo-inventario/inventario-producto/modificar-inventario-producto";
    }

    @GetMapping("/modulo-inventario/inventario-producto/eliminar")
    public String eliminarInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProductoService.eliminarInventarioProducto(inventarioProducto);
        return "redirect:/modulo-inventario/inventario-producto/lista";
    }

    @GetMapping("/modulo-inventario/inventario-producto/baja")
    public String darBajaInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProductoService.darBajaInventarioProducto(inventarioProducto);
        return "redirect:/modulo-inventario/inventario-producto/lista";
    }

}
