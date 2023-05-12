package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorModuloVentas {

    @GetMapping("/modulo-venta")
    public String produccionLacteos() {
        return "/pages/modulo-venta/modulo-venta";
    }

}
