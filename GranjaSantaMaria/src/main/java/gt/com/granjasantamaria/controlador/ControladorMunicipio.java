package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.Departamento;
import gt.com.granjasantamaria.modelo.Municipio;
import gt.com.granjasantamaria.servicio.DepartamentoService;
import gt.com.granjasantamaria.servicio.MunicipioService;
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
public class ControladorMunicipio {

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/modulo-ubicacion/municipio/lista")
    public String listadoMunicipios(Model model) {
        var municipios = municipioService.listadoMunicipios();
        model.addAttribute("municipios", municipios);
        return "/pages/modulo-ubicacion/municipio/municipio";
    }

    @GetMapping("/modulo-ubicacion/municipio/agregar")
    public String agregarMunicipio(Municipio municipio, Model model) {
        List<Departamento> listadoDepartamentos = departamentoService.listadoDepartamento();
        model.addAttribute("listadoDepartamentos", listadoDepartamentos);
        return "/pages/modulo-ubicacion/municipio/modificar-municipio";
    }

    @PostMapping("/modulo-ubicacion/municipio/guardar")
    public String guardarMunicipio(@Valid Municipio municipio, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            municipioService.guardarMunicipio(municipio);
            return "redirect:/modulo-ubicacion/municipio/lista";
        }
    }

    @GetMapping("/modulo-ubicacion/municipio/editar/{idMunicipio}")
    public String editarMunicipio(Municipio municipio, Model model) {
        List<Departamento> listadoDepartamentos = departamentoService.listadoDepartamento();
        model.addAttribute("listadoDepartamentos", listadoDepartamentos);

        municipio = municipioService.encontrarMunicipio(municipio);
        model.addAttribute("municipio", municipio);
        return "/pages/modulo-ubicacion/municipio/modificar-municipio";
    }

    @GetMapping("/modulo-ubicacion/municipio/eliminar")
    public String eliminarMunicipio(Municipio municipio) {
        municipioService.eliminarMunicipio(municipio);
        return "redirect:/modulo-ubicacion/municipio/lista";
    }

    @GetMapping("/modulo-ubicacion/municipio/baja")
    public String darBajaMunicipio(Municipio municipio) {
        municipioService.darBajaMunicipio(municipio);
        return "redirect:/modulo-ubicacion/municipio/lista";
    }

}
