package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class ControladorRazaGanado {

    @Autowired
    private RazaGanadoService razaGanadoService;

    @GetMapping("/modulo-ganado/raza-ganado/lista")
    public String listadoRazaGanado(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<RazaGanado> razaGanadoPage = razaGanadoService.obtenerListadoRazaGanadoPaginado(pageRequest);
        model.addAttribute("razaGanadoPage", razaGanadoPage);
        var razaGanados = razaGanadoPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("razaGanados", razaGanados);
        return "/pages/modulo-ganado/raza-ganado/raza-ganado";
    }

    @GetMapping("/modulo-ganado/raza-ganado/agregar")
    public String agregarRazaGanado(RazaGanado razaGanado) {
        return "/pages/modulo-ganado/raza-ganado/modificar-raza-ganado";
    }

    @PostMapping("/modulo-ganado/raza-ganado/guardar")
    public String guardarRazaGanado(@Valid RazaGanado razaGanado, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            razaGanadoService.guardarRazaGanado(razaGanado);
            return "redirect:/modulo-ganado/raza-ganado/lista";
        }
    }

    @GetMapping("/modulo-ganado/raza-ganado/editar/{idRazaGanado}")
    public String editarRazaGanado(RazaGanado razaGanado, Model model) {
        razaGanado = razaGanadoService.encontrarRazaGando(razaGanado);
        model.addAttribute("razaGanado", razaGanado);
        return "/pages/modulo-ganado/raza-ganado/modificar-raza-ganado";
    }

    @GetMapping("/modulo-ganado/raza-ganado/eliminar")
    public String eliminarRazaGanado(RazaGanado razaGanado) {
        razaGanadoService.eliminarRazaGanado(razaGanado);
        return "redirect:/modulo-ganado/raza-ganado/lista";
    }

    @GetMapping("/modulo-ganado/raza-ganado/baja")
    public String darBajaRazaGanado(RazaGanado razaGanado) {
        razaGanadoService.darBajaRazaGanado(razaGanado);
        return "redirect:/modulo-ganado/raza-ganado/lista";
    }

}
