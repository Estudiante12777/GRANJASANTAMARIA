package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.GanadoMacho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GanadoMachoService {

    List<GanadoMacho> obtenerListadoGanadoMachos();

    Page<GanadoMacho> obtenerGanadoMachoPaginado(Pageable pageable);

    void guardarGanadoMacho(GanadoMacho ganadoMacho);

    GanadoMacho encontrarGanadoMacho(GanadoMacho ganadoMacho);
    
    void darBajaGanadoMacho(GanadoMacho ganadoMacho);

}
