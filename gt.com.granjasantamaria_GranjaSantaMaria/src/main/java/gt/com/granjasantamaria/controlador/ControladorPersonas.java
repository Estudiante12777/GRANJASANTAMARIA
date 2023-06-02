package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gerso
 *
 * Este Controlador de Personas es responsable de manejar las peticiones
 * relacionadas con el módulo de personas y redirigir al usuario a la vista
 * correspondiente.
 */
@Controller
public class ControladorPersonas {

    /**
     * Este controladorPersonas lo unico que hace es crear una ruta de acceso, y
     * redirige a la vista modulo-persona
     *
     * @return la vista correspondiente al modulo de personas
     */
    @GetMapping("/modulo-persona")
    public String moduloPersonas() {
        return "/pages/modulo-persona/modulo-persona";
    }
}
