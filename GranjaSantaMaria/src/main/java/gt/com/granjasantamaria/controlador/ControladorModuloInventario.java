package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorModuloInventario {

    @GetMapping("/modulo-inventario")
    public String moduloInventario() {
        return "pages/modulo-inventario/modulo-inventario";
    }

}
