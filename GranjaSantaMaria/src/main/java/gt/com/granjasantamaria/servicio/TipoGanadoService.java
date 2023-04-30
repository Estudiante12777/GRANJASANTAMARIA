package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.TipoGanado;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface TipoGanadoService {

    // METODOS A IMPLEMENTAR 
    public List<TipoGanado> listadoTiposGanado();

    public void guardarTipoGanado(TipoGanado tipoGanado);

    public void eliminarTipoGanado(TipoGanado tipoGanado);

    public TipoGanado encontrarTipoGanado(TipoGanado tipoGanado);
    
    public void darBajaTipoGanado(TipoGanado tipoGanado); 
      
}
