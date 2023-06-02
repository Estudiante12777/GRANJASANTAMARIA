package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
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
public class ControladorGanadoHembra {

    @Autowired
    private GanadoHembraService ganadoHembraService;

    @Autowired
    private TipoGanadoService tipoGanadoService;

    @Autowired
    private RazaGanadoService razaGanadoService;

    @GetMapping("/modulo-ganado/ganado-hembra/lista")
    public String listadoGanadoMachods(Model model) {
        var ganadosHembra = ganadoHembraService.obtenerListadoGanadoHembras();
        model.addAttribute("ganadosHembra", ganadosHembra);
        return "/pages/modulo-ganado/ganado-hembra/ganado-hembra";
    }

    @GetMapping("/modulo-ganado/ganado-hembra/agregar")
    public String agregarGanadoHembra(GanadoHembra ganadoHembra, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        model.addAttribute("listaTiposGanado", listaTiposGanado);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        return "/pages/modulo-ganado/ganado-hembra/modificar-ganado-hembra";
    }

    @PostMapping("/modulo-ganado/ganado-hembra/guardar")
    public String guardarGanadoHembra(@Valid GanadoHembra ganadoHembra, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            ganadoHembraService.guardarGanadoHembra(ganadoHembra);
            return "redirect:/modulo-ganado/ganado-hembra/lista";
        }
    }

    @GetMapping("/modulo-ganado/ganado-hembra/editar/{idGanadoHembra}")
    public String editarGanadoHembra(GanadoHembra ganadoHembra, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        model.addAttribute("listaTiposGanado", listaTiposGanado);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        ganadoHembra = ganadoHembraService.encontrarGanadoHembra(ganadoHembra);
        model.addAttribute("ganadoHembra", ganadoHembra);
        return "/pages/modulo-ganado/ganado-hembra/modificar-ganado-hembra";
    }

    @GetMapping("/modulo-ganado/ganado-hembra/eliminar")
    public String eliminarGanadoHembra(GanadoHembra ganadoHembra) {
        ganadoHembraService.eliminarGanadoHembra(ganadoHembra);
        return "redirect:/modulo-ganado/ganado-hembra/lista";
    }

    @GetMapping("/modulo-ganado/ganado-hembra/baja")
    public String darBajaGanadoHembra(GanadoHembra ganadoHembra) {
        ganadoHembraService.darBajaGanadoHembra(ganadoHembra);
        return "redirect:/modulo-ganado/ganado-hembra/lista";
    }

}





