package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface DetalleHistorialClinicoMachoService {

    public List<DetalleHistorialClinicoMacho> obtenerListadoDetalleHistorialClinicoMachos();

    public void guardarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

    public void eliminarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

    public DetalleHistorialClinicoMacho encontrarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

    public void darBajaDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

}
