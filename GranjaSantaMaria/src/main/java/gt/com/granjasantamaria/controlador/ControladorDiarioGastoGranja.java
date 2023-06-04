package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import gt.com.granjasantamaria.servicio.DiarioGastoGranjaService;
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
public class ControladorDiarioGastoGranja {

    @Autowired
    private DiarioGastoGranjaService diarioGastoGranjaService;

    @GetMapping("/modulo-gasto/gasto-diario-granja/lista")
    public String listaDiarioGastosGranja(Model model) {
        var listaDiarioGastosGranja = diarioGastoGranjaService.obtenerListadoDiarioGastosGranja();
        model.addAttribute("listaDiarioGastosGranja", listaDiarioGastosGranja);
        return "/pages/modulo-gasto/gasto-diario-granja/gasto-diario-granja";
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/agregar")
    public String agregarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja, Model model) {
        return "/pages/modulo-gasto/gasto-diario-granja/modificar-gasto-diario-granja";
    }

    @PostMapping("/modulo-gasto/gasto-diario-granja/guardar")
    public String guardarDiarioGastoGranja(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) DiarioGastoGranja diarioGastoGranja, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            diarioGastoGranjaService.guardarDiarioGastoGranja(diarioGastoGranja);
            return "redirect:/modulo-gasto/gasto-diario-granja/lista";
        }
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/editar/{idDiarioGastoGranja}")
    public String editarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja, Model model) {
        diarioGastoGranja = diarioGastoGranjaService.encontrarDiarioGastoGranja(diarioGastoGranja);
        model.addAttribute("diarioGastoGranja", diarioGastoGranja);
        return "/pages/modulo-gasto/gasto-diario-granja/modificar-gasto-diario-granja";
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/eliminar")
    public String eliminarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        diarioGastoGranjaService.eliminarDiarioGastoGranja(diarioGastoGranja);
        return "redirect:/modulo-gasto/gasto-diario-granja/lista";
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/baja")
    public String darBajaDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        diarioGastoGranjaService.darBajaDiarioGastoGranja(diarioGastoGranja);
        return "redirect:/modulo-gasto/gasto-diario-granja/lista";
    }

}
