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
public class ControladorCategoriaGanado {

    @Autowired
    private CategoriaGanadoService categoriaGanadoService;

    @GetMapping("/modulo-ganado/categoria-ganado/lista")
    public String listaCategoriaGanado(Model model) {
        var categoriaDeGanados = categoriaGanadoService.listaCategoriasGanado();
        model.addAttribute("categoriaDeGanados", categoriaDeGanados);
        return "/pages/modulo-ganado/categoria-ganado/categoria-ganado";
    }

    @GetMapping("/modulo-ganado/categoria-ganado/agregar")
    public String agregarCategoriaGanado(CategoriaGanado categoriaGanado) {
        return "/pages/modulo-ganado/categoria-ganado/modificar-categoria-ganado";
    }

    @PostMapping("/modulo-ganado/categoria-ganado/guardar")
    public String guardarCategoriaGanado(@Valid CategoriaGanado categoriaGanado, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            categoriaGanadoService.guardarCategoriaGanado(categoriaGanado);
            return "redirect:/modulo-ganado/categoria-ganado/lista";
        }
    }

    @GetMapping("/modulo-ganado/categoria-ganado/editar/{idCategoriaGanado}")
    public String editarCategoriaGanado(CategoriaGanado categoriaGanado, Model model) {
        categoriaGanado = categoriaGanadoService.encontrarCategoriaGando(categoriaGanado);
        model.addAttribute("categoriaGanado", categoriaGanado);
        return "/pages/modulo-ganado/categoria-ganado/modificar-categoria-ganado";
    }

    @GetMapping("/modulo-ganado/categoria-ganado/eliminar")
    public String eliminarCategoriaGanado(CategoriaGanado categoriaGanado) {
        categoriaGanadoService.eliminarCategoriaGanado(categoriaGanado);
        return "redirect:/modulo-ganado/categoria-ganado/lista";
    }

    @GetMapping("/modulo-ganado/categoria-ganado/baja")
    public String darBajaCategoriaGanado(CategoriaGanado categoriaGanado) {
        categoriaGanadoService.darBajaCategoriaGanado(categoriaGanado);
        return "redirect:/modulo-ganado/categoria-ganado/lista";
    }

}
