package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorModuloGanado {

    @GetMapping("/modulo-ganado")
    public String ganado() {
        return "pages/modulo-ganado/modulo-ganado";
    }
}
