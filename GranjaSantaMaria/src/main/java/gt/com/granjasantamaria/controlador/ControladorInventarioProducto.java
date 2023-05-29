package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.InventarioProducto;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorInventarioProducto {

    @Autowired
    private InventarioProductoService inventarioProductoService;

    @Autowired
    private DetalleProductoService detalleProductoService;

    @GetMapping("/modulo-venta/inventario-producto/lista")
    public String obtenerListadoInventarioProductos(Model model) {
        var listaInventarioProductos = inventarioProductoService.obtenerListadoInventarioProductos();
        model.addAttribute("listaInventarioProductos", listaInventarioProductos);
        return "/pages/modulo-venta/inventario-producto/inventario-producto";
    }

    @GetMapping("/modulo-venta/inventario-producto/agregar")
    public String agregarProduccionDiariaLeche(InventarioProducto inventarioProducto, Model model) {
        List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listaDetalleProductos", listaDetalleProductos);
        return "/pages/modulo-venta/inventario-producto/modificar-inventario-producto";
    }

    @PostMapping("/modulo-venta/inventario-producto/guardar")
    public String guardarProduccionDiariaLeche(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) InventarioProducto inventarioProducto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pages/modulo-venta/inventario-producto/modificar-inventario-producto";
        }
        try {
            inventarioProductoService.guardarInventarioProducto(inventarioProducto);
            return "redirect:/modulo-venta/inventario-producto/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la producción diaria de leche.");
            List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
            model.addAttribute("listaDetalleProductos", listaDetalleProductos);
            return "/pages/modulo-venta/inventario-producto/modificar-inventario-producto";
        }
    }

    @GetMapping("/modulo-venta/inventario-producto/editar/{idInventarioProducto}")
    public String editarProduccionDiariaLeche(InventarioProducto inventarioProducto, Model model) {
        List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listaDetalleProductos", listaDetalleProductos);
        inventarioProducto = inventarioProductoService.encontrarInventarioProducto(inventarioProducto);
        model.addAttribute("produccionDiariaLeche", inventarioProducto);
        return "/pages/modulo-venta/inventario-producto/modificar-inventario-producto";
    }

    @GetMapping("/modulo-venta/inventario-producto/eliminar")
    public String eliminarProduccionDiariaLeche(InventarioProducto inventarioProducto) {
        inventarioProductoService.eliminarInventarioProducto(inventarioProducto);
        return "redirect:/modulo-venta/inventario-producto/lista";
    }

    @GetMapping("/modulo-venta/inventario-producto/baja")
    public String darBajaProduccionDiariaLeche(InventarioProducto inventarioProducto) {
        inventarioProductoService.darBajaInventarioProducto(inventarioProducto);
        return "redirect:/modulo-venta/inventario-producto/lista";
    }

}
