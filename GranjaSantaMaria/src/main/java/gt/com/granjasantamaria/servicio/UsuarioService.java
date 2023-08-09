package gt.com.granjasantamaria.servicio;

import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;
import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

    private final UsuarioDao usuarioDao;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioDao usuarioDao, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

    // Metodos para el manejo del perfil de usuario
    @Transactional
    public void actualizarContraseña(String nombreUsuario, String contraseniaActual, String contraseniaNueva) {
        Usuario usuario = usuarioDao.findByUsername(nombreUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException(nombreUsuario);
        }
        //Verificar la contraseña actual (implementa la logica para comparar contraseñas)
        if (!passwordEncoder.matches(contraseniaActual, usuario.getPassword())) {
            throw new IllegalArgumentException("Contraseña actual incorrecta");
        }
        // Actualizar la contraseña
        String encriptarNuevaContraenia = passwordEncoder.encode(contraseniaNueva);
        usuario.setPassword(encriptarNuevaContraenia);
        usuarioDao.save(usuario);
    }

}
