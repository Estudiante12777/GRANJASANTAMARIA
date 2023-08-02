package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorModuloUbicaciones {

    @GetMapping("/modulo-ubicacion")
    public String moduloUbicacion() {
        return "pages/modulo-ubicacion/modulo-ubicacion";
    }

}
