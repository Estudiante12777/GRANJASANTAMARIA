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
public class ControladorHistorialClinicoHembra {

    @Autowired
    private HistorialClinicoHembraService historialClinicoHembraService;

    @Autowired
    private GanadoHembraService ganadoHembraService;

    @GetMapping("/modulo-ganado/historial-clinico-hembra/lista")
    public String obtenerListadoHistorialClinicoHembras(Model model) {
        var historialClinicoHembras = historialClinicoHembraService.obtenerListadoHistorialClinicoHembras();
        model.addAttribute("historialClinicoHembras", historialClinicoHembras);
        return "/pages/modulo-ganado/historial-clinico-hembra/historial-clinico-hembra";
    }

    @GetMapping("/modulo-ganado/historial-clinico-hembra/agregar")
    public String agregarHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra, Model model) {
        List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
        model.addAttribute("listaGanados", listaGanados);
        return "/pages/modulo-ganado/historial-clinico-hembra/modificar-historial-clinico-hembra";
    }

    @PostMapping("/modulo-ganado/historial-clinico-hembra/guardar")
    public String guardarHistorialClinicoHembra(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) HistorialClinicoHembra historialClinicoHembra, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            historialClinicoHembraService.guardarHistorialClinicoHembra(historialClinicoHembra);
            return "redirect:/modulo-ganado/historial-clinico-hembra/lista";
        }
    }

    @GetMapping("/modulo-ganado/historial-clinico-hembra/editar/{idHistorialClinicoHembra}")
    public String editarHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra, Model model) {
        List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
        model.addAttribute("listaGanados", listaGanados);
        historialClinicoHembra = historialClinicoHembraService.encontrarHistorialClincioHembra(historialClinicoHembra);
        model.addAttribute("historialClinicoHembra", historialClinicoHembra);
        return "/pages/modulo-ganado/historial-clinico-hembra/modificar-historial-clinico-hembra";
    }

    @GetMapping("/modulo-ganado/historial-clinico-hembra/eliminar")
    public String eliminarHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra) {
        historialClinicoHembraService.eliminarHistorialClinicoHembra(historialClinicoHembra);
        return "redirect:/modulo-ganado/historial-clinico-hembra/lista";
    }

    @GetMapping("/modulo-ganado/historial-clinico-hembra/baja")
    public String darBajaHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra) {
        historialClinicoHembraService.darBajaHistorialClinicoHembra(historialClinicoHembra);
        return "redirect:/modulo-ganado/historial-clinico-hembra/lista";
    }

}
