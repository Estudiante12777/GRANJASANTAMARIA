package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.Departamento;
import gt.com.granjasantamaria.modelo.Pais;
import gt.com.granjasantamaria.servicio.DepartamentoService;
import gt.com.granjasantamaria.servicio.PaisService;
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
public class ControladorDepartamento {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private PaisService paisService;

    @GetMapping("/modulo-ubicacion/departamento/lista")
    public String listadoDepartamentos(Model model) {
        var departamentos = departamentoService.listadoDepartamento();
        model.addAttribute("departamentos", departamentos);
        return "/pages/modulo-ubicacion/departamento/departamento";
    }

    @GetMapping("/modulo-departamento/departamento/agregar")
    public String agregarDepartamento(Departamento departamento, Model model) {
        List<Pais> listadoPaises = paisService.listadoPais();
        model.addAttribute("listadoPaises", listadoPaises);
        return "/pages/modulo-ubicacion/departamento/modificar-departamento";
    }

    @PostMapping("/modulo-ubicacion/departamento/guardar")
    public String guardarDepartamento(@Valid Departamento departamento, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            departamentoService.guardarDepartamento(departamento);
            return "redirect:/modulo-ubicacion/departamento/lista";
        }
    }

    @GetMapping("/modulo-ubicacion/departamento/editar/{idDepartamento}")
    public String editarDepartamento(Departamento departamento, Model model) {
        List<Pais> listadoPaises = paisService.listadoPais();
        model.addAttribute("listadoPaises", listadoPaises);

        departamento = departamentoService.encontrarDepartamento(departamento);
        model.addAttribute("departamento", departamento);
        return "/pages/modulo-ubicacion/departamento/modificar-departamento";
    }

    @GetMapping("/modulo-ubicacion/departamento/eliminar")
    public String eliminarDepartamento(Departamento departamento) {
        departamentoService.eliminarDepartamento(departamento);
        return "redirect:/modulo-ubicacion/departamento/lista";
    }

    @GetMapping("/modulo-ubicacion/departamento/baja")
    public String darBajaDepartamento(Departamento departamento) {
        departamentoService.darBajaDepartamento(departamento);
        return "redirect:/modulo-ubicacion/departamento/lista";
    }

}
