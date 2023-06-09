package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorHistorialClinicioMacho {

    @Autowired
    private HistorialClinicioMachoService historialClinicioMachoService;

    @Autowired
    private GanadoMachoService ganadoMachoService;

    @GetMapping("/modulo-ganado/historial-clinico-macho/lista")
    public String obtenerListadoHistorialClinicoMachos(Model model) {
        var historialClinicoMachos = historialClinicioMachoService.obtenerListadoHistorialClinicoMachos();
        model.addAttribute("historialClinicoMachos", historialClinicoMachos);
        return "/pages/modulo-ganado/historial-clinico-macho/historial-clinico-macho";
    }

    @GetMapping("/modulo-ganado/historial-clinico-macho/agregar")
    public String agregarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho, Model model) {
        List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos();
        model.addAttribute("listaGanados", listaGanados);
        return "/pages/modulo-ganado/historial-clinico-macho/modificar-historial-clinico-macho";
    }

    @PostMapping("/modulo-ganado/historial-clinico-macho/guardar")
    public String guardarHistorialClinicoMacho(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) HistorialClinicoMacho historialClinicoMacho, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            historialClinicioMachoService.guardarHistorialClinicoMacho(historialClinicoMacho);
            return "redirect:/modulo-ganado/historial-clinico-macho/lista";
        }
    }

    @GetMapping("/modulo-ganado/historial-clinico-macho/editar/{idHistorialClinicoMacho}")
    public String editarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho, Model model) {
        List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos();
        model.addAttribute("listaGanados", listaGanados);
        historialClinicoMacho = historialClinicioMachoService.encontrarHistorialClinicoMacho(historialClinicoMacho);
        model.addAttribute("historialClinicoMacho", historialClinicoMacho);
        return "/pages/modulo-ganado/historial-clinico-macho/modificar-historial-clinico-macho";
    }

    @GetMapping("/modulo-ganado/historial-clinico-macho/eliminar")
    public String eliminarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho) {
        historialClinicioMachoService.eliminarHistorialClinicoMacho(historialClinicoMacho);
        return "redirect:/modulo-ganado/historial-clinico-macho/lista";
    }

    @GetMapping("/modulo-ganado/historial-clinico-macho/baja")
    public String darBajaHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho) {
        historialClinicioMachoService.darBajaHistorialClinicoMacho(historialClinicoMacho);
        return "redirect:/modulo-ganado/historial-clinico-macho/lista";
    }

}
