package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControladorModuloProducto {

    @GetMapping("/modulo-producto")
    public String moduloProducto() {
        return "/pages/modulo-producto/modulo-producto";
    }

}
