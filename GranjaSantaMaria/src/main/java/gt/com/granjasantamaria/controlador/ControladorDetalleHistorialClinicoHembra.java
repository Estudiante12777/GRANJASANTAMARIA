package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
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
public class ControladorDetalleHistorialClinicoHembra {

    private final HistorialClinicoHembraService historialClinicoHembraService;

    private final DetalleHistorialClinicoHembraService detalleHistorialClinicoHembraService;

    @Autowired
    public ControladorDetalleHistorialClinicoHembra(HistorialClinicoHembraService historialClinicoHembraService, DetalleHistorialClinicoHembraService detalleHistorialClinicoHembraService) {
        this.historialClinicoHembraService = historialClinicoHembraService;
        this.detalleHistorialClinicoHembraService = detalleHistorialClinicoHembraService;
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/lista")
    public String obtenerListadoDetalleHistorialClinicoHembra(@RequestParam("idHistorialClinicoHembra") Long idHistorialClinicoHembra, @RequestParam(defaultValue = "0", required = false) int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<DetalleHistorialClinicoHembra> detalleHistorialClinicoHembraPage = detalleHistorialClinicoHembraService.obtenerListadoDetalleHistorialClinicoHembraPaginado(idHistorialClinicoHembra, pageRequest);
        model.addAttribute("detalleHistorialClinicoHembraPage", detalleHistorialClinicoHembraPage);
        var detalleHistorialClinicoHembras = detalleHistorialClinicoHembraPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("detalleHistorialClinicoHembras", detalleHistorialClinicoHembras);
        model.addAttribute("idHistorialClinicoHembra", idHistorialClinicoHembra);
        return "pages/modulo-ganado/detalle-historial-clinico-hembra/detalle-historial-clinico-hembra";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/agregar")
    public String agregarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra, Model model) {
        List<HistorialClinicoHembra> listaGanados = historialClinicoHembraService.obtenerListadoHistorialClinicoHembras();
        model.addAttribute("listaGanados", listaGanados);
        return "pages/modulo-ganado/detalle-historial-clinico-hembra/modificar-detalle-historial-clinico-hembra";
    }

    @PostMapping("/modulo-ganado/detalle-historial-clinico-hembra/guardar")
    public String guardarDetalleHistorialClinicoHembra(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) DetalleHistorialClinicoHembra detalleHistorialClinicoHembra, BindingResult bindingResult) throws Exception {
        FieldError fieldError = bindingResult.getFieldError();
        if (fieldError != null) {
            String fieldName = fieldError.getField();
            throw new Exception("Error el campo, " + fieldName + " no puede estar vacío el campo");
        } else {
            detalleHistorialClinicoHembraService.guardarDetalleHistorialClinicoHembra(detalleHistorialClinicoHembra);
            return "redirect:/modulo-ganado/historial-clinico-hembra/lista";
        }
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/editar/{idDetalleHistorialClinicoHembra}")
    public String editarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra, Model model) {
        List<HistorialClinicoHembra> listaGanados = historialClinicoHembraService.obtenerListadoHistorialClinicoHembras();
        model.addAttribute("listaGanados", listaGanados);
        detalleHistorialClinicoHembra = detalleHistorialClinicoHembraService.encontrarDetalleHistorialClinicoHembra(detalleHistorialClinicoHembra);
        model.addAttribute("detalleHistorialClinicoHembra", detalleHistorialClinicoHembra);
        return "pages/modulo-ganado/detalle-historial-clinico-hembra/modificar-detalle-historial-clinico-hembra";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/eliminar")
    public String eliminarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        detalleHistorialClinicoHembraService.eliminarDetalleHistorialClinicoHembra(detalleHistorialClinicoHembra);
        return "redirect:/modulo-ganado/detalle-historial-clinico-hembra/lista";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/baja")
    public String darBajaDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        detalleHistorialClinicoHembraService.darBajaDetalleHistorialClinicoHembra(detalleHistorialClinicoHembra);
        return "redirect:/modulo-ganado/historial-clinico-hembra/lista";
    }

}
