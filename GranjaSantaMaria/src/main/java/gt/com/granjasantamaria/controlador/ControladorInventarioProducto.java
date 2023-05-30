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
        List<InventarioProducto> inventarioProductos = inventarioProductoService.obtenerListadoInventarioProductos();
        model.addAttribute("inventarioProductos", inventarioProductos);
        return "pages/modulo-venta/inventario-producto/inventario-producto";
    }

    @GetMapping("/modulo-venta/inventario-producto/agregar")
    public String agregarInventarioProducto(InventarioProducto inventarioProducto, Model model) {
        List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listaDetalleProductos", listaDetalleProductos);
        return "/pages/modulo-venta/inventario-producto/modificar-inventario-producto";
    }

    @PostMapping("/modulo-venta/inventario-producto/guardar")
    public String guardarInventarioProducto(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) InventarioProducto inventarioProducto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pages/modulo-venta/inventario-producto/modificar-inventario-producto";
        } else {
            List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
            model.addAttribute("listaDetalleProductos", listaDetalleProductos);
            inventarioProductoService.guardarInventarioProducto(inventarioProducto);
            model.addAttribute("listaDetalleProductos", listaDetalleProductos);
            return "redirect:/modulo-venta/inventario-producto/lista";
        }
    }

    @GetMapping("/modulo-venta/inventario-producto/editar/{idInventarioProducto}")
    public String editarInventarioProducto(InventarioProducto inventarioProducto, Model model) {
        List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listaDetalleProductos", listaDetalleProductos);
        inventarioProducto = inventarioProductoService.encontrarInventarioProducto(inventarioProducto);
        model.addAttribute("inventarioProducto", inventarioProducto);
        return "/pages/modulo-venta/inventario-producto/modificar-inventario-producto";
    }

    @GetMapping("/modulo-venta/inventario-producto/eliminar")
    public String eliminarInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProductoService.eliminarInventarioProducto(inventarioProducto);
        return "redirect:/modulo-venta/inventario-producto/lista";
    }

    @GetMapping("/modulo-venta/inventario-producto/baja")
    public String darBajaInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProductoService.darBajaInventarioProducto(inventarioProducto);
        return "redirect:/modulo-venta/inventario-producto/lista";
    }

}
