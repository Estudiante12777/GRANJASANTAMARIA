package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        var produccionDiariaLeches = produccionDiariaLecheService.obtenerListaProduccionDiariaLeche();
        model.addAttribute("produccionDiariaLeches", produccionDiariaLeches);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/produccion-diaria-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-leche")
    public String listaTotalProduccionLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        var produccionDiariaLeches = produccionDiariaLecheService.obtenerListaTotalProduccionLeche();
        model.addAttribute("produccionDiariaLeches", produccionDiariaLeches);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-diaria-leche")
    public String listaTotalProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        var produccionDiariaLeches = produccionDiariaLecheService.obtenerListaProduccionDiariaLeche();
        model.addAttribute("produccionDiariaLeches", produccionDiariaLeches);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-diaria-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha")
    public String listaTotalProduccionFecha(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        var produccionDiariaLeches = produccionDiariaLecheService.obtenerListaTotalProduccionLeche();
        model.addAttribute("produccionDiariaLeches", produccionDiariaLeches);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/encontrar-total-produccion-fecha")
    public String encontrarTotalProduccionFecha(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, Model model) {
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        // Llamar al servicio que hace la consulta
        List<ProduccionDiariaLeche> totalProduccionesFecha = produccionDiariaLecheService.encontrarTotalProduccionFecha(inicio, fin);
        // Añadir el resultado al modelo
        model.addAttribute("totalProduccionesFecha", totalProduccionesFecha);
        // Devolver el nombre de la vista
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/agregar")
    public String agregarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        List<Ganado> listaGanados = ganadoService.listadoGanado();
        model.addAttribute("listaGanados", listaGanados);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/modificar-produccion-diaria-leche";
    }

    @PostMapping("/modulo-produccion-lacteos/produccion-diaria-leche/guardar")
    public String guardarProduccionDiariaLeche(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) ProduccionDiariaLeche produccionDiariaLeche, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Ganado> listaGanados = ganadoService.listadoGanado();
            model.addAttribute("listaGanados", listaGanados);
            return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/modificar-produccion-diaria-leche";
        }

        try {
            produccionDiariaLecheService.guardarProduccionDiariaLeche(produccionDiariaLeche);
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la producción diaria de leche.");
            List<Ganado> listaGanados = ganadoService.listadoGanado();
            model.addAttribute("listaGanados", listaGanados);
            return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/modificar-produccion-diaria-leche";
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
        produccionDiariaLecheService.darDeBajaProduccionDiariaLeche(produccionDiariaLeche);
        return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
    }

}
