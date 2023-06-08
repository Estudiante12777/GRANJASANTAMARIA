package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.Producto;
import gt.com.granjasantamaria.servicio.ProductoService;
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
public class ControladorProducto {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/modulo-producto/producto/lista")
    public String obtenerListadoProductos(Model model) {
        var productos = productoService.obtenerListadoProductos();
        model.addAttribute("productos", productos);
        return "/pages/modulo-producto/producto/producto";
    }

    @GetMapping("/modulo-producto/producto/agregar")
    public String agregarProveedor(Producto producto, Model model) {
        return "/pages/modulo-producto/producto/modificar-producto";
    }

    @PostMapping("/modulo-producto/producto/guardar")
    public String guardarProducto(@Valid Producto prdoProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            productoService.guardarProducto(prdoProducto);
            return "redirect:/modulo-producto/producto/lista";
        }
    }

    @GetMapping("/modulo-producto/producto/editar/{idProducto}")
    public String editarProducto(Producto producto, Model model) {
        producto = productoService.encontranProducto(producto);
        model.addAttribute("producto", producto);
        return "/pages/modulo-producto/producto/modificar-producto";
    }

    @GetMapping("/modulo-producto/producto/eliminar")
    public String eliminarProducto(Producto producto) {
        productoService.eliminarProducto(producto);
        return "redirect:/modulo-producto/producto/lista";
    }

    @GetMapping("/modulo-producto/producto/baja")
    public String darBajaProducto(Producto producto) {
        productoService.darBajaProducto(producto);
        return "redirect:/modulo-producto/producto/lista";
    }

}