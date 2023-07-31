package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.HistorialClinicoMacho;
import java.util.List;

public interface HistorialClinicioMachoService {

    List<HistorialClinicoMacho> obtenerListadoHistorialClinicoMachos();

    void guardarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho);

    HistorialClinicoMacho encontrarHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho);

    void darBajaHistorialClinicoMacho(HistorialClinicoMacho historialClinicoMacho);

}
