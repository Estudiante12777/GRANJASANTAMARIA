package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.GanadoMacho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GanadoMachoService {

    public List<GanadoMacho> obtenerListadoGanadoMachos();

    public Page<GanadoMacho> obtenerGanadoMachoPaginado(Pageable pageable);

    public void guardarGanadoMacho(GanadoMacho ganadoMacho);

    public void eliminarGanadoMacho(GanadoMacho ganadoMacho);

    public GanadoMacho encontrarGanadoMacho(GanadoMacho ganadoMacho);
    
    public void darBajaGanadoMacho(GanadoMacho ganadoMacho); 

}
