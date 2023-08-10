package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/modulo-perfil-usuario/cambiar-contrasena")
    public String actualizarContrasena() {
        return "pages/modulo-perfil/cambiar-contrasena/cambiar-contrasena";
    }

    @GetMapping("/modulo-perfil-usuario/cambiar-nombre-usuario")
    public String actualizarNonbreUsuario() {
        return "pages/modulo-perfil/cambiar-nombre-usuario/cambiar-nombre-usuario";
    }

    @PostMapping("/cambiar-contrasena")
    public String cambiarContrasenia(@RequestParam("contraseniaActual") String contraseniaActual, @RequestParam("contraseniaNueva") String contraseniaNueva, Principal principal) {
        String nombreUsuario = principal.getName();
        usuarioService.cambiarContrasenia(nombreUsuario, contraseniaActual, contraseniaNueva);
        return "redirect:/login";
    }

    @PostMapping("/cambiar-nombre-usuario")
    public String cambiarNombreUsuario(@RequestParam("nuevoNombreUsuario") String nuevoNombreUsuario, @RequestParam("contraseniaActual") String contraseniaActual, Principal principal) {
        String nombreUsuario = principal.getName();
        usuarioService.cambiarNombreUsuario(nombreUsuario, contraseniaActual, nuevoNombreUsuario);
        return "redirect:/login";
    }

}
