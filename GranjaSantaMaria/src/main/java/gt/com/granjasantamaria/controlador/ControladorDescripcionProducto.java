package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
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
public class ControladorDescripcionProducto {

    @Autowired
    private DescripcionProductoService descripcionProductoService;

    @GetMapping("/modulo-venta/descripcion-producto/lista")
    public String obtenerListadoDescripcionProductos(Model model) {
        var productos = descripcionProductoService.obtenerListadoDescripcionProductos();
        model.addAttribute("productos", productos);
        return "/pages/modulo-venta/descripcion-producto/producto";
    }

    @GetMapping("/modulo-venta/descripcion-producto/agregar")
    public String agregarDescripcionProducto(Producto producto, Model model) {
        return "/pages/modulo-venta/descripcion-producto/modificar-descripcion-producto";
    }

    @PostMapping("/modulo-venta/descripcion-producto/guardar")
    public String guardarDescripcionProducto(@Valid DescripcionProducto descripcionProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            descripcionProductoService.guardarDescripcionProducto(descripcionProducto);
            return "redirect:/modulo-venta/descripcion-producto/lista";
        }
    }

    @GetMapping("/modulo-venta/descripcion-producto/editar/{idDescripcionProducto}")
    public String editarDescripcionProducto(DescripcionProducto descripcionProducto, Model model) {
        descripcionProducto = descripcionProductoService.encontrarDescripcionProducto(descripcionProducto);
        model.addAttribute("producto", descripcionProducto);
        return "/pages/modulo-venta/descripcion-producto/modificar-descripcion-producto";
    }

    @GetMapping("/modulo-venta/descripcion-producto/eliminar")
    public String eliminarDescripcionProducto(DescripcionProducto descripcionProducto) {
        descripcionProductoService.eliminarDescripcionProducto(descripcionProducto);
        return "redirect:/modulo-venta/descripcion-producto/lista";
    }

    @GetMapping("/modulo-venta/descripcion-producto/baja")
    public String darBajaDescripcionProducto(DescripcionProducto descripcionProducto) {
        descripcionProductoService.darBajaDescripcionProducto(descripcionProducto);
        return "redirect:/modulo-venta/descripcion-producto/lista";
    }

}
