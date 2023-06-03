package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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
    private GanadoHembraService ganadoHembraService;

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/lista")
    public String listaProduccionDiariaLeche(Model model) {
        var listaProduccionDiariaLeche = produccionDiariaLecheService.obtenerListaProduccionDiariaLeche();
        model.addAttribute("listaProduccionDiariaLeche", listaProduccionDiariaLeche);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/produccion-diaria-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-leche")
    public String listaTotalProduccionLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        var listaTotalProduccionLeche = produccionDiariaLecheService.obtenerListaTotalProduccionLeche();
        model.addAttribute("listaTotalProduccionLeche", listaTotalProduccionLeche);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-diaria-leche")
    public String listaTotalProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        var listaTotalProduccionDiariaLeche = produccionDiariaLecheService.obtenerListaProduccionDiariaLeche();
        model.addAttribute("listaTotalProduccionDiariaLeche", listaTotalProduccionDiariaLeche);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-diaria-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha")
    public String listaTotalProduccionFecha(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        var listaTotalProduccionFecha = produccionDiariaLecheService.obtenerListaTotalProduccionLeche();
        model.addAttribute("listaTotalProduccionFecha", listaTotalProduccionFecha);
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
        List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadoHembras();
        // Filtrar la lista de ganado para mostrar solo vacas y novillas
        List<GanadoHembra> listaVacasNovillas = listaGanados.stream()
                .filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Vaca")
                || ganado.getTipoGanado().getNombreTipoGanado().equals("Novilla"))
                .collect(Collectors.toList());
        System.out.println("Lista de ganados: " + listaGanados);
        System.out.println("Lista de vacas y novillas: " + listaVacasNovillas);
        model.addAttribute("listaGanados", listaVacasNovillas);
        return "/pages/modulo-produccion-lacteos/produccion-diaria-leche/modificar-produccion-diaria-leche";
    }

    @PostMapping("/modulo-produccion-lacteos/produccion-diaria-leche/guardar")
    public String guardarProduccionDiariaLeche(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) ProduccionDiariaLeche produccionDiariaLeche, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            produccionDiariaLecheService.guardarProduccionDiariaLeche(produccionDiariaLeche);
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        }
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/editar/{idProduccionDiariaLeche}")
    public String editarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadoHembras();
        // Filtrar la lista de ganado para mostrar solo vacas y novillas
        List<GanadoHembra> listaVacasNovillas = listaGanados.stream()
                .filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Vaca")
                || ganado.getTipoGanado().getNombreTipoGanado().equals("Novilla"))
                .collect(Collectors.toList());
        System.out.println("Lista de ganados: " + listaGanados);
        System.out.println("Lista de vacas y novillas: " + listaVacasNovillas);
        model.addAttribute("listaGanados", listaVacasNovillas);
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
