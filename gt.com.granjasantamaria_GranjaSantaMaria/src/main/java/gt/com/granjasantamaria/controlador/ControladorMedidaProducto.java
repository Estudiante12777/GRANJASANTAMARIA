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
public class ControladorMedidaProducto {

    @Autowired
    private MedidaProductoService medidaProductoService;

    @GetMapping("/modulo-venta/medida-producto/lista")
    public String obtenerListadoMedidaProductos(Model model) {
        var medidaProductos = medidaProductoService.obtenerListadoMedidaProductos();
        model.addAttribute("medidaProductos", medidaProductos);
        return "/pages/modulo-venta/medida-producto/medida-producto";
    }

    @GetMapping("/modulo-venta/medida-producto/agregar")
    public String agregarMedidaProducto(MedidaProducto medidaProducto, Model model) {
        return "/pages/modulo-venta/medida-producto/modificar-medida-producto";
    }

    @PostMapping("/modulo-venta/medida-producto/guardar")
    public String guardarMedidaProducto(@Valid MedidaProducto medidaProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            medidaProductoService.guardarMedidaProducto(medidaProducto);
            return "redirect:/modulo-venta/medida-producto/lista";
        }
    }

    @GetMapping("/modulo-venta/medida-producto/editar/{idMedidaProducto}")
    public String editarMedidaProducto(MedidaProducto medidaProducto, Model model) {
        medidaProducto = medidaProductoService.encontrarMedidaProducto(medidaProducto);
        model.addAttribute("medidaProducto", medidaProducto);
        return "/pages/modulo-venta/medida-producto/modificar-medida-producto";
    }

    @GetMapping("/modulo-venta/medida-producto/eliminar")
    public String eliminarMedidaProducto(MedidaProducto medidaProducto) {
        medidaProductoService.eliminarMedidaProducto(medidaProducto);
        return "redirect:/modulo-venta/medida-producto/lista";
    }

    @GetMapping("/modulo-venta/medida-producto/baja")
    public String darBajaMedidaProducto(MedidaProducto medidaProducto) {
        medidaProductoService.darBajaMedidaProducto(medidaProducto);
        return "redirect:/modulo-venta/medida-producto/lista";
    }

}
