package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoHembra;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface DetalleHistorialClinicoHembraService {

    public List<DetalleHistorialClinicoHembra> obtenerListadoDetalleHistorialClinicoHembras();

    public void guardarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    public void eliminarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    public DetalleHistorialClinicoHembra encontrarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    public void darBajaDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

}
