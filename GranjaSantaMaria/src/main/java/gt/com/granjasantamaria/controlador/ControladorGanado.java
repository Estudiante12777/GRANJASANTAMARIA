package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorGanado {

    @GetMapping("/ganado")
    public String ganado(Model model) {
        return "/pages/ganado/ganado";
    }
}
