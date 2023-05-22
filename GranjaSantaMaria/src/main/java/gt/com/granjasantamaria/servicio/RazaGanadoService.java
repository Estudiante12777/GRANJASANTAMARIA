package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.*;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface RazaGanadoService {

    public List<RazaGanado> listadoRazasGanado();

    public void guardarRazaGanado(RazaGanado razaGanado);

    public void eliminarRazaGanado(RazaGanado razaGanado);

    public RazaGanado encontrarRazaGando(RazaGanado razaGanado);

    public void darBajaRazaGanado(RazaGanado razaGanado);

}
