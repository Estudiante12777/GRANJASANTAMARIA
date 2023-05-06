package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Ganado;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface GanadoService {

    // METODOS A IMPLEMENTAR
    public List<Ganado> listadoGanado();

    public void guardarGanado(Ganado ganado);

    public void eliminarGanado(Ganado ganado);

    public Ganado encontrarGanado(Ganado ganado);

    public void darBajaGanado(Ganado ganado);

}
