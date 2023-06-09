package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorDescripcionProducto {

    @Autowired
    private DescripcionProductoService descripcionProductoService;

    @GetMapping("/modulo-producto/descripcion-producto/lista")
    public String obtenerListadoDescripcionProductos(Model model) {
        var descripcionProductos = descripcionProductoService.obtenerListadoDescripcionProductos();
        model.addAttribute("descripcionProductos", descripcionProductos);
        return "/pages/modulo-producto/descripcion-producto/descripcion-producto";
    }

    @GetMapping("/modulo-producto/descripcion-producto/agregar")
    public String agregarDescripcionProducto(DescripcionProducto descripcionProducto, Model model) {
        return "/pages/modulo-producto/descripcion-producto/modificar-descripcion-producto";
    }

    @PostMapping("/modulo-producto/descripcion-producto/guardar")
    public String guardarDescripcionProducto(@Valid DescripcionProducto descripcionProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            descripcionProductoService.guardarDescripcionProducto(descripcionProducto);
            return "redirect:/modulo-producto/descripcion-producto/lista";
        }
    }

    @GetMapping("/modulo-producto/descripcion-producto/editar/{idDescripcionProducto}")
    public String editarDescripcionProducto(DescripcionProducto descripcionProducto, Model model) {
        descripcionProducto = descripcionProductoService.encontrarDescripcionProducto(descripcionProducto);
        model.addAttribute("descripcionProducto", descripcionProducto);
        return "/pages/modulo-producto/descripcion-producto/modificar-descripcion-producto";
    }

    @GetMapping("/modulo-producto/descripcion-producto/eliminar")
    public String eliminarDescripcionProducto(DescripcionProducto descripcionProducto) {
        descripcionProductoService.eliminarDescripcionProducto(descripcionProducto);
        return "redirect:/modulo-producto/descripcion-producto/lista";
    }

    @GetMapping("/modulo-producto/descripcion-producto/baja")
    public String darBajaDescripcionProducto(DescripcionProducto descripcionProducto) {
        descripcionProductoService.darBajaDescripcionProducto(descripcionProducto);
        return "redirect:/modulo-producto/descripcion-producto/lista";
    }

}
