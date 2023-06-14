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
public class ControladorTipoGanado {

    @Autowired
    private TipoGanadoService tipoGanadoService;

    @GetMapping("/modulo-ganado/tipo-ganado/lista")
    public String listaTipoGanado(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<TipoGanado> tipoGanadoPage = tipoGanadoService.obtenerListadoTipoGanadoPaginado(pageRequest);
        model.addAttribute("tipoGanadoPage", tipoGanadoPage);
        var tipoDeGanados = tipoGanadoPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("tipoDeGanados", tipoDeGanados);
        return "/pages/modulo-ganado/tipo-ganado/tipo-ganado";
    }

    @GetMapping("/modulo-ganado/tipo-ganado/agregar")
    public String agregarTipoGanado(TipoGanado tipoGanado) {
        return "/pages/modulo-ganado/tipo-ganado/modificar-tipo-ganado";
    }

    @PostMapping("/modulo-ganado/tipo-ganado/guardar")
    public String guardarTipoGanado(@Valid TipoGanado tipoGanado, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error no puede estar vacio el campo");
        } else {
            tipoGanadoService.guardarTipoGanado(tipoGanado);
            return "redirect:/modulo-ganado/tipo-ganado/lista";
        }
    }

    @GetMapping("/modulo-ganado/tipo-ganado/editar/{idTipoGanado}")
    public String editarTipoGando(TipoGanado tipoGanado, Model model) {
        tipoGanado = tipoGanadoService.encontrarTipoGanado(tipoGanado);
        model.addAttribute("tipoGanado", tipoGanado);
        return "/pages/modulo-ganado/tipo-ganado/modificar-tipo-ganado";
    }

    @GetMapping("/modulo-ganado/tipo-ganado/eliminar")
    public String eliminarTipoGanado(TipoGanado tipoGando) {
        tipoGanadoService.eliminarTipoGanado(tipoGando);
        return "redirect:/modulo-ganado/tipo-ganado/lista";
    }

    @GetMapping("/modulo-ganado/tipo-ganado/baja")
    public String darBajaTipoGanado(TipoGanado tipoGando) {
        tipoGanadoService.darBajaTipoGanado(tipoGando);
        return "redirect:/modulo-ganado/tipo-ganado/lista";
    }

}
