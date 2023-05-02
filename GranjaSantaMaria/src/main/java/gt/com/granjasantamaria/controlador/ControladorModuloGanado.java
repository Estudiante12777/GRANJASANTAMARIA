package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorModuloGanado {

    @GetMapping("/modulo-ganado")
    public String ganado(Model model) {
        return "/pages/modulo-ganado/modulo-ganado";
    }
}
