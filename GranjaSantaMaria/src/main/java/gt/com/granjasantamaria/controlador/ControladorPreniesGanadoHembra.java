package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import gt.com.granjasantamaria.modelo.PreniesGanadoHembra;
import gt.com.granjasantamaria.servicio.GanadoHembraService;
import gt.com.granjasantamaria.servicio.PreniesGanadoHembraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorPreniesGanadoHembra {

    @Autowired
    private PreniesGanadoHembraService preniesGanadoHembraService;

    @Autowired
    private GanadoHembraService ganadoHembraService;

    @GetMapping("/modulo-ganado/prenies-ganado/lista")
    public String obtenerListadoPreniesGanadoHembraPaginado(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<PreniesGanadoHembra> preniesGanadoHembraPage = preniesGanadoHembraService.obtenerListadoPreniesGanadoHembraPaginado(pageRequest);
        model.addAttribute("preniesGanadoHembraPage", preniesGanadoHembraPage);
        var preniesGanadoHembras = preniesGanadoHembraPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("preniesGanadoHembras", preniesGanadoHembras);
        return "/pages/modulo-ganado/prenies-ganado/prenies-ganado";
    }

    @GetMapping("/modulo-ganado/prenies-ganado/agregar")
    public String agregarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra, Model model) {
        List<GanadoHembra> ganadoHembras = ganadoHembraService.obtenerListadoGanadosHembra();
        List<GanadoHembra> ganadoHembraList = ganadoHembras.stream().filter(ganadoHembra -> ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Novilla") || ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Vaca")).collect(Collectors.toList());
        model.addAttribute("ganadoHembras", ganadoHembraList);
        return "/pages/modulo-ganado/prenies-ganado/modificar-prenies-ganado";
    }

    @PostMapping("/modulo-ganado/prenies-ganado/guardar")
    public String guardarPreniesGanadoHembra(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) PreniesGanadoHembra preniesGanadoHembra, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<GanadoHembra> ganadoHembras = ganadoHembraService.obtenerListadoGanadosHembra();
            model.addAttribute("ganadoHembras", ganadoHembras);
            return "/pages/modulo-ganado/prenies-ganado/modificar-prenies-ganado";
        }
        try {
            preniesGanadoHembraService.guardarPreniesGanadoHembra(preniesGanadoHembra);
            return "redirect:/modulo-ganado/prenies-ganado/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el registro de preñes.");
            List<GanadoHembra> ganadoHembras = ganadoHembraService.obtenerListadoGanadosHembra();
            model.addAttribute("ganadoHembras", ganadoHembras);
            return "/pages/modulo-ganado/prenies-ganado/modificar-prenies-ganado";
        }
    }

    @GetMapping("/modulo-ganado/prenies-ganado/editar/{idPreniesGanadoHembra}")
    public String editarPreniesGanadoHembra(@PathVariable("idPreniesGanadoHembra") Long idPreniesGanadoHembra, PreniesGanadoHembra preniesGanadoHembra, Model model) {
        List<GanadoHembra> ganadoHembras = ganadoHembraService.obtenerListadoGanadosHembra();
        List<GanadoHembra> ganadoHembraList = ganadoHembras.stream().filter(ganadoHembra -> ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Novilla") || ganadoHembra.getTipoGanado().getNombreTipoGanado().equals("Vaca")).collect(Collectors.toList());
        model.addAttribute("ganadoHembras", ganadoHembraList);
        model.addAttribute("idPreniesGanadoHembra", idPreniesGanadoHembra);
        preniesGanadoHembra = preniesGanadoHembraService.encontrarPreniesGanadoHembra(preniesGanadoHembra);
        model.addAttribute("preniesGanadoHembra", preniesGanadoHembra);
        return "/pages/modulo-ganado/prenies-ganado/modificar-prenies-ganado";
    }

    @GetMapping("/modulo-ganado/prenies-ganado/eliminar")
    public String eliminarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra) {
        preniesGanadoHembraService.eliminarPreniesGanadoHembra(preniesGanadoHembra);
        return "redirect:/modulo-ganado/prenies-ganado/lista";
    }

    @GetMapping("/modulo-ganado/prenies-ganado/baja")
    public String darBajaGanadoMacho(PreniesGanadoHembra preniesGanadoHembra) {
        preniesGanadoHembraService.darBajaPreniesGanadoHembra(preniesGanadoHembra);
        return "redirect:/modulo-ganado/prenies-ganado/lista";
    }

}
