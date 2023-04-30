package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.TipoGanado;
import gt.com.granjasantamaria.servicio.TipoGanadoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorTipoGanado {

    @Autowired
    private TipoGanadoService tipoGanadoService;

    @GetMapping("/ganado/tipo-ganado/lista")
    public String listaTipoGanado(Model model) {
        var tipoDeGanados = tipoGanadoService.listadoTiposDeGanado();
        model.addAttribute("tipoDeGanados", tipoDeGanados);
        return "/pages/ganado/tipo-ganado/tipo-ganado";
    }

    @GetMapping("/ganado/tipo-ganado/agregar")
    public String agregarTipoGanado(TipoGanado tipoGanado) {
        return "/pages/ganado/tipo-ganado/modificar-tipo-ganado";
    }

    @PostMapping("/ganado/tipo-ganado/guardar")
    public String guardarTipoGanado(@Valid TipoGanado tipoGanado, Errors errores) throws Exception {
        if (errores.hasErrors()) {
            throw new Exception("Error no puede estar vacio el campo");
        } else {
            tipoGanadoService.guardarTipoGanado(tipoGanado);
            return "redirect:/ganado/tipo-ganado/lista";
        }
    }

    @GetMapping("/ganado/tipo-ganado/editar/{idTipoGanado}")
    public String editarTipoGando(TipoGanado tipoGanado, Model model) {
        tipoGanado = tipoGanadoService.encontrarTipoGanado(tipoGanado);
        model.addAttribute("tipoGanado", tipoGanado);
        return "/pages/ganado/tipo-ganado/modificar-tipo-ganado";
    }

    @GetMapping("/ganado/tipo-ganado/eliminar")
    public String eliminarTipoGanado(TipoGanado tipoGando) {
        tipoGanadoService.eliminarTipoGanado(tipoGando);
        return "redirect:/ganado/tipo-ganado/lista";
    }

    @GetMapping("/ganado/tipo-ganado/baja")
    public String darBajaTipoGanado(TipoGanado tipoGando) {
        tipoGanadoService.darBajaTipoGanado(tipoGando);
        return "redirect:/ganado/tipo-ganado/lista";
    }
}
