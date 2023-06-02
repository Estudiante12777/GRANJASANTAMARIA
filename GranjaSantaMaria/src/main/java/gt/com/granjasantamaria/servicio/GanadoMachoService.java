package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.GanadoMacho;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface GanadoMachoService {

    public List<GanadoMacho> obtenerListadoGanadoMachos();

    public void guardarGanadoMacho(GanadoMacho ganadoMacho);

    public void eliminarGanadoMacho(GanadoMacho ganadoMacho);

    public GanadoMacho encontrarGanadoMacho(GanadoMacho ganadoMacho);
    
    public void darBajaGanadoMacho(GanadoMacho ganadoMacho); 

}
