package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.Ganado;
import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import gt.com.granjasantamaria.servicio.GanadoService;
import gt.com.granjasantamaria.servicio.ProduccionDiariaLecheService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorProduccionDiaraLeche {

    @Autowired
    private ProduccionDiariaLecheService produccionDiariaLecheService;

    @Autowired
    private GanadoService ganadoService;

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/lista")
    public String listaProduccionDiariaLeche(Model model) {
        var produccionDiariaLeches = produccionDiariaLecheService.listaProduccionDiariaLeche();
        model.addAttribute("produccionDiariaLeches", produccionDiariaLeches);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/produccion-diaria-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/agregar")
    public String agregarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        List<Ganado> listaGanados = ganadoService.listadoGanado();
        model.addAttribute("listaGanados", listaGanados);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/modificar-produccion-diaria-leche";
    }

    @PostMapping("/modulo-produccion-lacteos/produccion-diaria-leche/guardar")
    public String guardarProduccionDiariaLeche(@Valid ProduccionDiariaLeche produccionDiariaLeche, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            produccionDiariaLecheService.guardarProduccionDiariaLeche(produccionDiariaLeche);
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        }
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/editar/{idProduccionDiariaLeche}")
    public String editarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        List<Ganado> listaGanados = ganadoService.listadoGanado();
        model.addAttribute("listaGanados", listaGanados);

        produccionDiariaLeche = produccionDiariaLecheService.encontrarProduccionDiariaLeche(produccionDiariaLeche);
        model.addAttribute("produccionDiariaLeche", produccionDiariaLeche);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/modificar-produccion-diaria-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/eliminar")
    public String eliminarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        produccionDiariaLecheService.eliminarProduccionDiariaLeche(produccionDiariaLeche);
        return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/baja")
    public String darBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        produccionDiariaLecheService.darBajaProduccionDiariaLeche(produccionDiariaLeche);
        return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
    }

}
