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
public class ControladorGanadoMacho {

    @Autowired
    private GanadoMachoService ganadoMachoService;

    @Autowired
    private TipoGanadoService tipoGanadoService;

    @Autowired
    private RazaGanadoService razaGanadoService;

    @GetMapping("/modulo-ganado/ganado-macho/lista")
    public String listadoGanadoMachods(Model model) {
        var ganadosMacho = ganadoMachoService.obtenerListadoGanadoMachos();
        model.addAttribute("ganadosMacho", ganadosMacho);
        return "/pages/modulo-ganado/ganado-macho/ganado-macho";
    }

    @GetMapping("/modulo-ganado/ganado-macho/agregar")
    public String agregarGanadoMacho(GanadoMacho ganadoMacho, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        model.addAttribute("listaTiposGanado", listaTiposGanado);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        return "/pages/modulo-ganado/ganado-macho/modificar-ganado-macho";
    }

    @PostMapping("/modulo-ganado/ganado-macho/guardar")
    public String guardarGanadoMacho(@Valid GanadoMacho ganadoMacho, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            ganadoMachoService.guardarGanadoMacho(ganadoMacho);
            return "redirect:/modulo-ganado/ganado-macho/lista";
        }
    }

    @GetMapping("/modulo-ganado/ganado-macho/editar/{idGanadoMacho}")
    public String editarGanadoMacho(GanadoMacho ganadoMacho, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        model.addAttribute("listaTiposGanado", listaTiposGanado);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        ganadoMacho = ganadoMachoService.encontrarGanadoMacho(ganadoMacho);
        model.addAttribute("ganadoMacho", ganadoMacho);
        return "/pages/modulo-ganado/ganado-macho/modificar-ganado-macho";
    }

    @GetMapping("/modulo-ganado/ganado-macho/eliminar")
    public String eliminarGanadoMacho(GanadoMacho ganadoMacho) {
        ganadoMachoService.eliminarGanadoMacho(ganadoMacho);
        return "redirect:/modulo-ganado/ganado-macho/lista";
    }

    @GetMapping("/modulo-ganado/ganado-macho/baja")
    public String darBajaGanadoMacho(GanadoMacho ganadoMacho) {
        ganadoMachoService.darBajaGanadoMacho(ganadoMacho);
        return "redirect:/modulo-ganado/ganado-macho/lista";
    }

}
