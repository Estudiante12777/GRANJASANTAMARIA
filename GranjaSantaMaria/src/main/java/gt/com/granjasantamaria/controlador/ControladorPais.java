package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.CategoriaGanado;
import gt.com.granjasantamaria.modelo.Pais;
import gt.com.granjasantamaria.servicio.PaisService;
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
public class ControladorPais {

    @Autowired
    private PaisService paisService;

    @GetMapping("/modulo-ubicacion/pais/lista")
    public String listaPais(Model model) {
        var paises = paisService.listadoPais();
        model.addAttribute("paises", paises);
        return "/pages/modulo-ubicacion/pais/pais";
    }

    @GetMapping("/modulo-ubicacion/pais/agregar")
    public String agregarPais(Pais pais) {
        return "/pages/modulo-ubicacion/pais/modificar-pais";
    }

    @PostMapping("/modulo-ubicacion/pais/guardar")
    public String guardarPais(@Valid Pais pais, Errors errores) throws Exception {
        if (errores.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            paisService.guardarPais(pais);
            return "redirect:/modulo-ubicacion/pais/lista";
        }
    }

    @GetMapping("/modulo-ubicacion/pais/editar/{idPais}")
    public String editarPais(Pais pais, Model model) {
        pais = paisService.encontrarPais(pais);
        model.addAttribute("pais", pais);
        return "/pages/modulo-ubicacion/pais/modificar-pais";
    }

    @GetMapping("/modulo-ubicacion/pais/eliminar")
    public String eliminarPais(Pais pais) {
        paisService.eliminarPais(pais);
        return "redirect:/modulo-ubicacion/pais/lista";
    }

    @GetMapping("/modulo-ubicacion/pais/baja")
    public String darBajaPais(Pais pais) {
        paisService.darBajaPais(pais);
        return "redirect:/modulo-ubicacion/pais/lista";
    }

}
