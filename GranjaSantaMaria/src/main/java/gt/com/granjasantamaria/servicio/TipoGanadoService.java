package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.TipoGanado;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface TipoGanadoService {

    // CONTRATO -- METODOS A IMPLEMENTAR 
    public List<TipoGanado> listadoTiposDeGanado();

    public void guardarTipoGanado(TipoGanado tipoGanado);

    public void eliminarTipoGanado(TipoGanado tipoGanado);

    public TipoGanado encontrarTipoGanado(TipoGanado tipoGanado);
    
    public void darBajaTipoGanado(TipoGanado tipoGanado); 
      
}
