package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorModuloBalance {

    @GetMapping("/modulo-balance")
    public String balance() {
        return "/pages/modulo-balance/modulo-balance";
    }
}
