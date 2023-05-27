package gt.com.granjasantamaria.controlador;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorContenedorProducto {

    @Autowired
    private ContenedorProductoService contenedorProductoService;

    @GetMapping("/modulo-venta/contenedor-producto/lista")
    public String obtenerListadoProductos(Model model) {
        var contenedorProducto = contenedorProductoService.obtenerListadoContenedorProductos();
        model.addAttribute("contenedorProducto", contenedorProducto);
        return "/pages/modulo-venta/contenedor-producto/medida-producto";
    }

    @GetMapping("/modulo-venta/contenedor-producto/agregar")
    public String agregarProveedor(ContenedorProducto contenedorProducto, Model model) {
        return "/pages/modulo-venta/contenedor-producto/medida-producto";
    }

    @PostMapping("/modulo-venta/contenedor-producto/guardar")
    public String guardarProducto(@Valid ContenedorProducto contenedorProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            contenedorProductoService.guardarContenedorProducto(contenedorProducto);
            return "redirect:/modulo-venta/contenedor-producto/lista";
        }
    }

    @GetMapping("/modulo-venta/contenedor-producto/editar/{idMedidaProducto}")
    public String editarProducto(ContenedorProducto contenedorProducto, Model model) {
        contenedorProducto = contenedorProductoService.encontrarContenedorProducto(contenedorProducto);
        model.addAttribute("medidaProducto", contenedorProducto);
        return "/pages/modulo-venta/contenedor-producto/medida-producto";
    }

    @GetMapping("/modulo-venta/contenedor-producto/eliminar")
    public String eliminarProducto(ContenedorProducto contenedorProducto) {
        contenedorProductoService.eliminarContenedorProducto(contenedorProducto);
        return "redirect:/modulo-venta/contenedor-producto/lista";
    }

    @GetMapping("/modulo-venta/contenedor-producto/baja")
    public String darBajaProducto(ContenedorProducto contenedorProducto) {
        contenedorProductoService.darBajaContenedorProducto(contenedorProducto);
        return "redirect:/modulo-venta/contenedor-producto/lista";
    }

}
