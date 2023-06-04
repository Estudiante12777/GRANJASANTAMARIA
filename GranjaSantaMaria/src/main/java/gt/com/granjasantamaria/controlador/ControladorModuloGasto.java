package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorModuloGasto {

    @GetMapping("/modulo-gasto")
    public String moduloGasto() {
        return "/pages/modulo-gasto/modulo-gasto";
    }

}
