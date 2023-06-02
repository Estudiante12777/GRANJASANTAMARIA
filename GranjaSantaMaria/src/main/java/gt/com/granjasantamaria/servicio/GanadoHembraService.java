package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface GanadoHembraService {

    public List<GanadoHembra> obtenerListadoGanadoHembras();

    public void guardarGanadoHembra(GanadoHembra ganadoHembra);

    public void eliminarGanadoHembra(GanadoHembra ganadoHembra);

    public GanadoHembra encontrarGanadoHembra(GanadoHembra ganadoHembra);
    
    public void darBajaGanadoHembra(GanadoHembra ganadoHembra); 

}
