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
public class ControladorMunicipio {

    private final PaisService paisService;

    private final DepartamentoService departamentoService;

    private final MunicipioService municipioService;

    @Autowired
    public ControladorMunicipio(PaisService paisService, DepartamentoService departamentoService, MunicipioService municipioService) {
        this.paisService = paisService;
        this.departamentoService = departamentoService;
        this.municipioService = municipioService;
    }

    @GetMapping("/modulo-ubicacion/municipio/lista")
    public String listadoMunicipios(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<Municipio> municipioPage = municipioService.listadoMunicipioPaginado(pageRequest);
        model.addAttribute("municipioPage", municipioPage);
        var municipios = municipioPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("municipios", municipios);
        return "pages/modulo-ubicacion/municipio/municipio";
    }

    @GetMapping("/modulo-ubicacion/municipio/agregar")
    public String agregarMunicipio(Municipio municipio, Model model) {
        List<Departamento> listadoDepartamentos = departamentoService.listadoDepartamento();
        model.addAttribute("listadoDepartamentos", listadoDepartamentos);
        List<Pais> listadoPaises = paisService.listadoPais();
        model.addAttribute("listadoPaises", listadoPaises);
        return "pages/modulo-ubicacion/municipio/modificar-municipio";
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
        List<Pais> listadoPaises = paisService.listadoPais();
        model.addAttribute("listadoPaises", listadoPaises);
        municipio = municipioService.encontrarMunicipio(municipio);
        model.addAttribute("municipio", municipio);
        return "pages/modulo-ubicacion/municipio/modificar-municipio";
    }

    @GetMapping("/modulo-ubicacion/municipio/baja")
    public String darBajaMunicipio(Municipio municipio) {
        municipioService.darBajaMunicipio(municipio);
        return "redirect:/modulo-ubicacion/municipio/lista";
    }

}
