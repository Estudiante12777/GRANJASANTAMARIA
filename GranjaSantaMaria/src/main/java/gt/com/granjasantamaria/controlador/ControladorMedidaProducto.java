package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class ControladorMedidaProducto {

    @Autowired
    private MedidaProductoService medidaProductoService;

    @GetMapping("/modulo-producto/medida-producto/lista")
    public String obtenerListadoMedidaProductos(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<MedidaProducto> medidaProductoPage = medidaProductoService.obtenerListadoMedidaProductoPaginado(pageRequest);
        model.addAttribute("medidaProductoPage", medidaProductoPage);
        var medidaProductos = medidaProductoPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("medidaProductos", medidaProductos);
        return "pages/modulo-producto/medida-producto/medida-producto";
    }

    @GetMapping("/modulo-producto/medida-producto/agregar")
    public String agregarMedidaProducto(MedidaProducto medidaProducto, Model model) {
        return "pages/modulo-producto/medida-producto/modificar-medida-producto";
    }

    @PostMapping("/modulo-producto/medida-producto/guardar")
    public String guardarMedidaProducto(@Valid MedidaProducto medidaProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            medidaProductoService.guardarMedidaProducto(medidaProducto);
            return "redirect:/modulo-producto/medida-producto/lista";
        }
    }

    @GetMapping("/modulo-producto/medida-producto/editar/{idMedidaProducto}")
    public String editarMedidaProducto(MedidaProducto medidaProducto, Model model) {
        medidaProducto = medidaProductoService.encontrarMedidaProducto(medidaProducto);
        model.addAttribute("medidaProducto", medidaProducto);
        return "pages/modulo-producto/medida-producto/modificar-medida-producto";
    }

    @GetMapping("/modulo-producto/medida-producto/eliminar")
    public String eliminarMedidaProducto(MedidaProducto medidaProducto) {
        medidaProductoService.eliminarMedidaProducto(medidaProducto);
        return "redirect:/modulo-producto/medida-producto/lista";
    }

    @GetMapping("/modulo-producto/medida-producto/baja")
    public String darBajaMedidaProducto(MedidaProducto medidaProducto) {
        medidaProductoService.darBajaMedidaProducto(medidaProducto);
        return "redirect:/modulo-producto/medida-producto/lista";
    }

}
