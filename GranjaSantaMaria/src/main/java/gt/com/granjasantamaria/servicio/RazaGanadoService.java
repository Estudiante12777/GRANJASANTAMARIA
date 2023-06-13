package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author gerso
 */
public interface RazaGanadoService {

    public List<RazaGanado> listadoRazasGanado();

    public Page<RazaGanado> obtenerListadoRazaGanadoPaginado(Pageable pageable);

    public void guardarRazaGanado(RazaGanado razaGanado);

    public void eliminarRazaGanado(RazaGanado razaGanado);

    public RazaGanado encontrarRazaGando(RazaGanado razaGanado);

    public void darBajaRazaGanado(RazaGanado razaGanado);

}
