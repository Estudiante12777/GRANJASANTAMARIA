package gt.com.granjasantamaria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPerfil {

    @GetMapping("/modulo-perfil-usuario")
    public String perfilUsuario() {
        return "pages/modulo-perfil/perfil-usuario/perfil-usuario";
    }

}
