package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.CategoriaGanado;
import gt.com.granjasantamaria.servicio.CategoriaGanadoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorCategoriaGanado {

    @Autowired
    private CategoriaGanadoService categoriaGanadoService;

    @GetMapping("/ganado/categoria-ganado/lista")
    public String listaCategoriaGanado(Model model) {
        var categoriaDeGanados = categoriaGanadoService.listaCategoriasGanado();
        model.addAttribute("categoriaDeGanados", categoriaDeGanados);
        return "/pages/ganado/categoria-ganado/categoria-ganado";
    }

    @GetMapping("/ganado/categoria-ganado/agregar")
    public String agregarCategoriaGanado(CategoriaGanado categoriaGanado) {
        return "/pages/ganado/categoria-ganado/modificar-categoria-ganado";
    }

    @PostMapping("/ganado/categoria-ganado/guardar")
    public String guardarCategoriaGanado(@Valid CategoriaGanado categoriaGanado, Errors errores) throws Exception {
        if (errores.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            categoriaGanadoService.guardarCategoriaGanado(categoriaGanado);
            return "redirect:/ganado/categoria-ganado/lista";
        }
    }

    @GetMapping("/ganado/categoria-ganado/editar/{idCategoriaGanado}")
    public String editarCategoriaGanado(CategoriaGanado categoriaGanado, Model model) {
        categoriaGanado = categoriaGanadoService.encontrarCategoriaGando(categoriaGanado);
        model.addAttribute("categoriaGanado", categoriaGanado);
        return "/pages/ganado/categoria-ganado/modificar-categoria-ganado";
    }

    @GetMapping("/ganado/categoria-ganado/eliminar")
    public String eliminarCategoriaGanado(CategoriaGanado categoriaGanado) {
        categoriaGanadoService.eliminarCategoriaGanado(categoriaGanado);
        return "redirect:/ganado/categoria-ganado/lista";
    }

    @GetMapping("/ganado/categoria-ganado/baja")
    public String darBajaCategoriaGanado(CategoriaGanado categoriaGanado) {
        categoriaGanadoService.darBajaCategoriaGanado(categoriaGanado);
        return "redirect:/ganado/categoria-ganado/lista";
    }

}
