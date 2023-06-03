package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.HistorialClinicoHembra;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface HistorialClinicoHembraService {

    public List<HistorialClinicoHembra> obtenerListadoHistorialClinicoHembras();

    public void guardarHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra);

    public void eliminarHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra);

    public HistorialClinicoHembra encontrarHistorialClincioHembra(HistorialClinicoHembra historialClinicoHembra);

    public void darBajaHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra);

}
