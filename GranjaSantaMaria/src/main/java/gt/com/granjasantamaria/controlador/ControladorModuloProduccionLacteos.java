package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorModuloProduccionLacteos {

    @GetMapping("/modulo-produccion-lacteos")
    public String produccionLacteos() {
        return "/pages/modulo-produccion-lacteos/modulo-produccion-lacteos";
    }
}
