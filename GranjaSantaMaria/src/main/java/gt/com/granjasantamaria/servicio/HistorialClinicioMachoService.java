package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.HistorialClinicoMacho;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface HistorialClinicioMachoService {

    public List<HistorialClinicoMacho> obtenerListadoHistorialClinicoMachos();

    public void guardarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho);

    public void eliminarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho);

    public HistorialClinicoMacho encontrarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho);

    public void darBajaHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho);

}
