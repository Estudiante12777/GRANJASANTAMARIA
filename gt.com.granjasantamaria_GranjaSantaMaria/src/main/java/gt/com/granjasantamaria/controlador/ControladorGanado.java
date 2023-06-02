package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
import javax.servlet.ServletContext;
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
public class ControladorGanado {

    @Autowired
    private GanadoService ganadoService;

    @Autowired
    private TipoGanadoService tipoGanadoService;

    @Autowired
    private RazaGanadoService razaGanadoService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/modulo-ganado/ganado/lista")
    public String listadoGanados(Model model) {
        var ganados = ganadoService.listadoGanados();
        model.addAttribute("ganados", ganados);
        return "/pages/modulo-ganado/ganado/ganado";
    }

    @GetMapping("/modulo-ganado/ganado/agregar")
    public String agregarGanado(Ganado ganado, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        model.addAttribute("listaTiposGanado", listaTiposGanado);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        return "/pages/modulo-ganado/ganado/modificar-ganado";
    }

    @PostMapping("/modulo-ganado/ganado/guardar")
    public String guardarGanado(@Valid Ganado ganado, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            ganadoService.guardarGanado(ganado);
            return "redirect:/modulo-ganado/ganado/lista";
        }
    }

    @GetMapping("/modulo-ganado/ganado/editar/{idGanado}")
    public String editarGanado(Ganado ganado, Model model
    ) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        model.addAttribute("listaTiposGanado", listaTiposGanado);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        ganado = ganadoService.encontrarGanado(ganado);
        model.addAttribute("ganado", ganado);
        return "/pages/modulo-ganado/ganado/modificar-ganado";
    }

    @GetMapping("/modulo-ganado/ganado/eliminar")
    public String eliminarGanado(Ganado ganado
    ) {
        ganadoService.eliminarGanado(ganado);
        return "redirect:/modulo-ganado/ganado/lista";
    }

    @GetMapping("/modulo-ganado/ganado/baja")
    public String darBajaGanado(Ganado ganado
    ) {
        ganadoService.darBajaGanado(ganado);
        return "redirect:/modulo-ganado/ganado/lista";
    }

}
