package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorDepartamento {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private PaisService paisService;

    @GetMapping("/modulo-ubicacion/departamento/lista")
    public String listadoDepartamentos(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<Departamento> departamentoPage = departamentoService.listadoDepartamentoPaginado(pageRequest);
        model.addAttribute("departamentoPage", departamentoPage);
        var departamentos = departamentoPage.getContent().stream().limit(10).collect(Collectors.toList());
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
