package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorModuloGasto {

    @GetMapping("/modulo-gasto")
    public String moduloGasto() {
        return "pages/modulo-gasto/modulo-gasto";
    }

}
