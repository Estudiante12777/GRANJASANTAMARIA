package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GanadoHembraService {

    public List<GanadoHembra> obtenerListadoGanadosHembra();

    public Page<GanadoHembra> obtenerGanadoHembraPaginado(Pageable pageable);

    public void guardarGanadoHembra(GanadoHembra ganadoHembra);

    public void eliminarGanadoHembra(GanadoHembra ganadoHembra);

    public GanadoHembra encontrarGanadoHembra(GanadoHembra ganadoHembra);
    
    public void darBajaGanadoHembra(GanadoHembra ganadoHembra); 

}
