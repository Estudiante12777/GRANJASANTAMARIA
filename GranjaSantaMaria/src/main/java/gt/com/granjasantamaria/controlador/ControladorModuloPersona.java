package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorModuloPersona {

    @GetMapping("/modulo-persona")
    public String moduloPersonas() {
        return "pages/modulo-persona/modulo-persona";
    }
}
