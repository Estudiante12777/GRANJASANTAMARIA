package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.servicio.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorDetalleProducto {

    @Autowired
    private DetalleProductoService detalleProductoService;

    @GetMapping("/modulo-venta/detalle-producto/lista")
    public String obtenerListadoProductos(Model model) {
        var productos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("productos", productos);
        return "/pages/modulo-venta/detalle-producto/producto";
    }

    @GetMapping("/modulo-venta/detalle-producto/agregar")
    public String agregarProveedor(DetalleProducto detalleProducto, Model model) {
        return "/pages/modulo-venta/detalle-producto/modificar-producto";
    }

    @PostMapping("/modulo-venta/detalle-producto/guardar")
    public String guardarProducto(@Valid DetalleProducto detalleProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            detalleProductoService.guardarDetalleProducto(detalleProducto);
            return "redirect:/modulo-venta/detalle-producto/lista";
        }
    }

    @GetMapping("/modulo-venta/detalle-producto/editar/{idProducto}")
    public String editarProducto(DetalleProducto detalleProducto, Model model) {
        detalleProducto = detalleProductoService.encontrarDetalleProducto(detalleProducto);
        model.addAttribute("producto", detalleProducto);
        return "/pages/modulo-venta/detalle-producto/modificar-producto";
    }

    @GetMapping("/modulo-venta/detalle-producto/eliminar")
    public String eliminarProducto(DetalleProducto detalleProducto) {
        detalleProductoService.eliminarDetalleProducto(detalleProducto);
        return "redirect:/modulo-venta/detalle-producto/lista";
    }

    @GetMapping("/modulo-venta/detalle-producto/baja")
    public String darBajaProducto(DetalleProducto detalleProducto) {
        detalleProductoService.darBajaDetalleProducto(detalleProducto);
        return "redirect:/modulo-venta/detalle-producto/lista";
    }

}
