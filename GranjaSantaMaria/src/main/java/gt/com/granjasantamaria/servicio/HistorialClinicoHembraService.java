package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.HistorialClinicoHembra;
import java.util.List;

public interface HistorialClinicoHembraService {

    List<HistorialClinicoHembra> obtenerListadoHistorialClinicoHembras();

    void guardarHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra);

    void eliminarHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra);

    HistorialClinicoHembra encontrarHistorialClincioHembra(HistorialClinicoHembra historialClinicoHembra);

    void darBajaHistorialClinicoHembra(HistorialClinicoHembra historialClinicoHembra);

}
