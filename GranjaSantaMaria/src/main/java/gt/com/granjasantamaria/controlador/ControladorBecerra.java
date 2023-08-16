package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.Becerra;
import gt.com.granjasantamaria.modelo.GanadoHembra;
import gt.com.granjasantamaria.servicio.BecerraService;
import gt.com.granjasantamaria.servicio.GanadoHembraService;
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
public class ControladorBecerra {

    private final GanadoHembraService ganadoHembraService;

    private final BecerraService becerraService;

    @Autowired
    public ControladorBecerra(GanadoHembraService ganadoHembraService, BecerraService becerraService) {
        this.ganadoHembraService = ganadoHembraService;
        this.becerraService = becerraService;
    }

    @GetMapping("/modulo-ganado/becerra/lista")
    public String listadoBecerras(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<Becerra> becerraPage = becerraService.listadoBecerrasPaginado(pageRequest);
        model.addAttribute("becerraPage", becerraPage);
        var becerras = becerraPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("becerras", becerras);
        return "pages/modulo-ganado/becerra/becerra";
    }

    @GetMapping("/modulo-ganado/becerra/agregar")
    public String agregarBecerra(Becerra becerra, Model model) {
        List<GanadoHembra> madres = ganadoHembraService.obtenerListadoGanadosHembra().stream().filter(ganadoHembra -> ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Vaca")).collect(Collectors.toList());
        List<GanadoHembra> becerras = ganadoHembraService.obtenerListadoGanadosHembra().stream().filter(ganadoHembra -> ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Becerra")).collect(Collectors.toList());
        model.addAttribute("madres", madres);
        model.addAttribute("becerras", becerras);
        return "pages/modulo-ganado/becerra/modificar-becerra";
    }

    @PostMapping("/modulo-ganado/becerra/guardar")
    public String guardarBecerra(@Valid Becerra becerra, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Errores de validacion: ");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
            throw new Exception(errorMessage.toString());
        } else {
            becerraService.guardarBecerra(becerra);
            return "redirect:/modulo-ganado/becerra/lista";
        }
    }

    @GetMapping("/modulo-ganado/becerra/editar/{idRelacionMadreBecerra}")
    public String editarBecerra(Becerra becerra, Model model) {
        becerra = becerraService.encontrarBecerra(becerra);
        List<GanadoHembra> madres = ganadoHembraService.obtenerListadoGanadosHembra().stream().filter(ganadoHembra -> ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Vaca")).collect(Collectors.toList());
        List<GanadoHembra> becerras = ganadoHembraService.obtenerListadoGanadosHembra().stream().filter(ganadoHembra -> ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Becerra")).collect(Collectors.toList());
        model.addAttribute("becerra", becerra);
        model.addAttribute("madres", madres);
        model.addAttribute("becerras", becerras);
        return "pages/modulo-ganado/becerra/modificar-becerra";
    }

    @GetMapping("/modulo-ganado/becerra/baja")
    public String darBajaBecerra(Becerra becerra) {
        becerraService.darBajaBecerra(becerra);
        return "redirect:/modulo-ganado/becerra/lista";
    }

}
