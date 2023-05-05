package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.CategoriaGanado;
import gt.com.granjasantamaria.modelo.Ganado;
import gt.com.granjasantamaria.modelo.TipoGanado;
import gt.com.granjasantamaria.servicio.CategoriaGanadoService;
import gt.com.granjasantamaria.servicio.GanadoService;
import gt.com.granjasantamaria.servicio.TipoGanadoService;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class ControladorGanado {

    @Autowired
    private GanadoService ganadoService;

    @Autowired
    private TipoGanadoService tipoGanadoService;

    @Autowired
    private CategoriaGanadoService categoriaGanadoService;

    @GetMapping("/modulo-ganado/ganado/lista")
    public String listaGanados(Model model) {
        var ganados = ganadoService.listadoGanados();
        model.addAttribute("ganados", ganados);
        return "/pages/modulo-ganado/ganado/ganado";
    }

    @GetMapping("/modulo-ganado/ganado/agregar")
    public String agregarGanado(Ganado ganado) {
        return "/pages/modulo-ganado/ganado/modificar-ganado";
    }

    @PostMapping("/modulo-ganado/ganado/guardar")
    public String guardarGanado(@Valid Ganado ganado, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            ganadoService.guardarGanado(ganado);
            return "redirect:/modulo-ganado/ganado/lista";
        }
    }

    @GetMapping("/modulo-ganado/ganado/editar/{idGanado}")
    public String editarGanado(Ganado ganado, Model model) {
        List<TipoGanado> listaTiposGanados = tipoGanadoService.listadoTiposGanado();
        model.addAttribute("listaTiposGanados", listaTiposGanados);

        List<CategoriaGanado> listaCategorias = categoriaGanadoService.listaCategoriasGanado();
        model.addAttribute("listaCategorias", listaCategorias);

        ganado = ganadoService.encontrarGanado(ganado);
        model.addAttribute("ganado", ganado);
        return "/pages/modulo-ganado/ganado/modificar-ganado";
    }

    @GetMapping("/modulo-ganado/ganado/eliminar")
    public String eliminarGanado(Ganado ganado) {
        ganadoService.eliminarGanado(ganado);
        return "redirect:/modulo-ganado/ganado/lista";
    }

    @GetMapping("/modulo-ganado/ganado/baja")
    public String darBajaGanado(Ganado ganado) {
        ganadoService.darBajaGanado(ganado);
        return "redirect:/modulo-ganado/ganado/lista";
    }

}
