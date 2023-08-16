package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.Becerro;
import gt.com.granjasantamaria.modelo.GanadoHembra;
import gt.com.granjasantamaria.modelo.GanadoMacho;
import gt.com.granjasantamaria.servicio.BecerroService;
import gt.com.granjasantamaria.servicio.GanadoHembraService;
import gt.com.granjasantamaria.servicio.GanadoMachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorBecerro {

    private final GanadoHembraService ganadoHembraService;

    private final GanadoMachoService ganadoMachoService;

    private final BecerroService becerroService;

    @Autowired
    public ControladorBecerro(GanadoHembraService ganadoHembraService, GanadoMachoService ganadoMachoService, BecerroService becerroService) {
        this.ganadoHembraService = ganadoHembraService;
        this.ganadoMachoService = ganadoMachoService;
        this.becerroService = becerroService;
    }

    @GetMapping("/modulo-ganado/becerro/lista")
    public String listadoBecerros(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<Becerro> becerroPage = becerroService.listadoBecerrosPaginado(pageRequest);
        model.addAttribute("becerroPage", becerroPage);
        var becerros = becerroPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("becerros", becerros);
        return "pages/modulo-ganado/becerro/becerro";
    }

    @GetMapping("/modulo-ganado/becerro/agregar")
    public String agregarBecerro(Becerro becerro, Model model) {
        List<GanadoHembra> madres = ganadoHembraService.obtenerListadoGanadosHembra().stream().filter(ganadoHembra -> ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Vaca")).collect(Collectors.toList());
        List<GanadoMacho> becerros = ganadoMachoService.obtenerListadoGanadoMachos().stream().filter(ganadoMacho -> ganadoMacho.getTipoGanado().getNombreTipoGanado().equals("Becerro")).collect(Collectors.toList());
        model.addAttribute("madres", madres);
        model.addAttribute("becerros", becerros);
        return "pages/modulo-ganado/becerro/modificar-becerro";
    }

    @PostMapping("/modulo-ganado/becerro/guardar")
    public String guardarBecerro(@Valid Becerro becerro, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Errores de validacion: ");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
            throw new Exception(errorMessage.toString());
        } else {
            becerroService.guardarBecerro(becerro);
            return "redirect:/modulo-ganado/becerro/lista";
        }
    }

    @GetMapping("/modulo-ganado/becerro/editar/{idRelacionMadreBecerro}")
    public String editarBecerro(Becerro becerro, Model model) {
        becerro = becerroService.encontrarBecerro(becerro);
        List<GanadoHembra> madres = ganadoHembraService.obtenerListadoGanadosHembra().stream().filter(ganadoHembra -> ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Vaca")).collect(Collectors.toList());
        List<GanadoMacho> becerros = ganadoMachoService.obtenerListadoGanadoMachos().stream().filter(ganadoMacho -> ganadoMacho.getTipoGanado().getNombreTipoGanado().equals("Becerro")).collect(Collectors.toList());
        model.addAttribute("becerro", becerro);
        model.addAttribute("madres", madres);
        model.addAttribute("becerros", becerros);
        return "pages/modulo-ganado/becerro/modificar-becerro";
    }

    @GetMapping("/modulo-ganado/becerro/baja")
    public String darBajaBecerro(Becerro becerro) {
        becerroService.darBajaBecerro(becerro);
        return "redirect:/modulo-ganado/becerro/lista";
    }

}
