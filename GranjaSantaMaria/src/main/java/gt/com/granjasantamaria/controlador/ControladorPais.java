package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorPais {

    private final PaisService paisService;

    @Autowired
    public ControladorPais(PaisService paisService) {
        this.paisService = paisService;
    }

    @GetMapping("/modulo-ubicacion/pais/lista")
    public String listaPais(Model model) {
        var paises = paisService.listadoPais();
        model.addAttribute("paises", paises);
        return "pages/modulo-ubicacion/pais/pais";
    }

    @GetMapping("/modulo-ubicacion/pais/agregar")
    public String agregarPais(Pais pais) {
        return "pages/modulo-ubicacion/pais/modificar-pais";
    }

    @PostMapping("/modulo-ubicacion/pais/guardar")
    public String guardarPais(@Valid Pais pais, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            paisService.guardarPais(pais);
            return "redirect:/modulo-ubicacion/pais/lista";
        }
    }

    @GetMapping("/modulo-ubicacion/pais/editar/{idPais}")
    public String editarPais(Pais pais, Model model) {
        pais = paisService.encontrarPais(pais);
        model.addAttribute("pais", pais);
        return "pages/modulo-ubicacion/pais/modificar-pais";
    }


    @GetMapping("/modulo-ubicacion/pais/baja")
    public String darBajaPais(Pais pais) {
        paisService.darBajaPais(pais);
        return "redirect:/modulo-ubicacion/pais/lista";
    }

}
