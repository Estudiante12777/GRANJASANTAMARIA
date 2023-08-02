package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;
import gt.com.granjasantamaria.modelo.HistorialClinicoMacho;
import gt.com.granjasantamaria.servicio.*;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorDetalleHistorialClinicoMacho {

    private final HistorialClinicioMachoService historialClinicioMachoService;

    private final DetalleHistorialClinicoMachoService detalleHistorialClinicoMachoService;

    @Autowired
    public ControladorDetalleHistorialClinicoMacho(HistorialClinicioMachoService historialClinicioMachoService, DetalleHistorialClinicoMachoService detalleHistorialClinicoMachoService) {
        this.historialClinicioMachoService = historialClinicioMachoService;
        this.detalleHistorialClinicoMachoService = detalleHistorialClinicoMachoService;
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/lista")
    public String obtenerListadoDetalleHistorialClinicoHembra(@RequestParam("idHistorialClinicoMacho") Long idHistorialClinicoMacho, @RequestParam(defaultValue = "0", required = false) int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<DetalleHistorialClinicoMacho> detalleHistorialClinicoMachoPage = detalleHistorialClinicoMachoService.obtenerListadoDetalleHistorialClinicoMachos(idHistorialClinicoMacho, pageRequest);
        model.addAttribute("detalleHistorialClinicoMachoPage", detalleHistorialClinicoMachoPage);
        var detalleHistorialClinicoMachos = detalleHistorialClinicoMachoPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("detalleHistorialClinicoMachos", detalleHistorialClinicoMachos);
        model.addAttribute("idHistorialClinicoMacho", idHistorialClinicoMacho);
        return "/pages/modulo-ganado/detalle-historial-clinico-macho/detalle-historial-clinico-macho";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/agregar")
    public String agregarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho, Model model) {
        List<HistorialClinicoMacho> listaGanados = historialClinicioMachoService.obtenerListadoHistorialClinicoMachos();
        model.addAttribute("listaGanados", listaGanados);
        return "/pages/modulo-ganado/detalle-historial-clinico-macho/modificar-detalle-historial-clinico-macho";
    }

    @PostMapping("/modulo-ganado/detalle-historial-clinico-macho/guardar")
    public String guardarDetalleHistorialClinicoMacho(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) DetalleHistorialClinicoMacho detalleHistorialClinicoMacho, BindingResult bindingResult) throws Exception {
        FieldError fieldError = bindingResult.getFieldError();
        if (fieldError != null) {
            String fieldName = fieldError.getField();
            throw new Exception("Error el campo, " + fieldName + " no puede estar vacío el campo");
        } else {
            detalleHistorialClinicoMachoService.guardarDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
            return "redirect:/modulo-ganado/historial-clinico-macho/lista";
        }
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/editar/{idDetalleHistorialClinicoMacho}")
    public String editarDetalleHistorialClinicoMacho(@PathVariable("idDetalleHistorialClinicoMacho") Long idDetalleHistorialClinicoMacho, DetalleHistorialClinicoMacho detalleHistorialClinicoMacho, Model model) {
        List<HistorialClinicoMacho> listaGanados = historialClinicioMachoService.obtenerListadoHistorialClinicoMachos();
        model.addAttribute("listaGanados", listaGanados);
        model.addAttribute("idDetalleHistorialClinicoMacho", idDetalleHistorialClinicoMacho);
        detalleHistorialClinicoMacho = detalleHistorialClinicoMachoService.encontrarDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
        model.addAttribute("detalleHistorialClinicoMacho", detalleHistorialClinicoMacho);
        return "/pages/modulo-ganado/detalle-historial-clinico-macho/modificar-detalle-historial-clinico-macho";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/baja/{idDetalleHistorialClinicoMacho}")
    public String darBajaDetalleHistorialClinicoMacho(@PathVariable("idDetalleHistorialClinicoMacho") Long idDetalleHistorialClinicoMacho) {
        DetalleHistorialClinicoMacho detalleHistorialClinicoMacho = new DetalleHistorialClinicoMacho();
        detalleHistorialClinicoMacho.setIdDetalleHistorialClinicoMacho(idDetalleHistorialClinicoMacho);
        detalleHistorialClinicoMachoService.darBajaDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
        return "redirect:/modulo-ganado/historial-clinico-macho/lista";
    }

}
