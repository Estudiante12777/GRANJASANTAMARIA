package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorHome {

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("titulo", "Granja Santa Maria");
        return "inicio";
    }
}
