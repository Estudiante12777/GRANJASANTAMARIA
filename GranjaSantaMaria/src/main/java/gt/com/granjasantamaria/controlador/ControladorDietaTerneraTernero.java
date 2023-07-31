package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DietaTerneraTernero;
import gt.com.granjasantamaria.servicio.DietaTerneraTerneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class ControladorDietaTerneraTernero {

    private final DietaTerneraTerneroService dietaTerneraTerneroService;

    @Autowired
    public ControladorDietaTerneraTernero(DietaTerneraTerneroService dietaTerneraTerneroService) {
        this.dietaTerneraTerneroService = dietaTerneraTerneroService;
    }

    @GetMapping("/modulo-ganado/dieta-ternera-ternero/lista")
    public String obtenerListadoDietaTerneraTerneroPaginado(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<DietaTerneraTernero> dietaTerneraTerneroPage = dietaTerneraTerneroService.obtenerListadoDietaTerneraTerneroPaginado(pageRequest);
        model.addAttribute("dietaTerneraTerneroPage", dietaTerneraTerneroPage);
        var dietaTerneraTernero = dietaTerneraTerneroPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("dietaTerneraTernero", dietaTerneraTernero);
        return "/pages/modulo-ganado/dieta-ternera-ternero/dieta-ternera-ternero";
    }

    @GetMapping("/modulo-ganado/dieta-ternera-ternero/agregar")
    public String agregarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero) {
        return "/pages/modulo-ganado/dieta-ternera-ternero/modificar-dieta-ternera-ternero";
    }

    @PostMapping("/modulo-ganado/dieta-ternera-ternero/guardar")
    public String guardarDietaTerneraTernero(@Valid DietaTerneraTernero dietaTerneraTernero, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            dietaTerneraTerneroService.guardarDietaTerneraTernero(dietaTerneraTernero);
            return "redirect:/modulo-ganado/dieta-ternera-ternero/lista";
        }
    }

    @GetMapping("/modulo-ganado/dieta-ternera-ternero/editar/{idDietaTerneraTernero}")
    public String editarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero, Model model) {
        dietaTerneraTernero = dietaTerneraTerneroService.encontrarDietaTerneraTernero(dietaTerneraTernero);
        model.addAttribute("dietaTerneraTernero", dietaTerneraTernero);
        return "/pages/modulo-ganado/dieta-ternera-ternero/modificar-dieta-ternera-ternero";
    }

    @GetMapping("/modulo-ganado/dieta-ternera-ternero/baja")
    public String darBajaDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero) {
        dietaTerneraTerneroService.darBajaDietaTerneraTernero(dietaTerneraTernero);
        return "redirect:/modulo-ganado/dieta-ternera-ternero/lista";
    }

}
