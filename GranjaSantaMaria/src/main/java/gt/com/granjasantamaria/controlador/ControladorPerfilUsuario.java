package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Access;
import java.security.Principal;

@Controller
public class ControladorPerfilUsuario {

    private final UsuarioService usuarioService;

    @Autowired
    public ControladorPerfilUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/modulo-perfil-usuario")
    public String perfilUsuario() {
        return "pages/modulo-perfil/perfil-usuario/perfil-usuario";
    }

    @GetMapping("/modulo-perfil-usuario/actualizar")
    public String actualizarInforamcion() {
        return "pages/modulo-perfil/actualizar-informacion/actualizar-informacion";
    }

    @PostMapping("/cambiar-contrasena")
    public String cambiarContraseña(@RequestParam("contraseniaActual") String contraseniaActual, @RequestParam("contraseniaNueva") String contraseniaNueva, Principal principal) {
        String nombreUsuario = principal.getName();
        usuarioService.actualizarContraseña(nombreUsuario, contraseniaActual, contraseniaNueva);
        return "redirect:/perfil?success";
    }

}
