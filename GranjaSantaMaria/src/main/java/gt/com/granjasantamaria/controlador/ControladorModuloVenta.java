package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorModuloVenta {

    @GetMapping("/modulo-venta")
    public String produccionLacteos() {
        return "pages/modulo-venta/modulo-venta";
    }

}
