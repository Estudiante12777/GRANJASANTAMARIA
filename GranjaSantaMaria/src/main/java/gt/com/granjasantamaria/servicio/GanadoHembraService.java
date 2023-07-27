package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GanadoHembraService {

    List<GanadoHembra> obtenerListadoGanadosHembra();

    Page<GanadoHembra> obtenerGanadoHembraPaginado(Pageable pageable);

    void guardarGanadoHembra(GanadoHembra ganadoHembra);

    void eliminarGanadoHembra(GanadoHembra ganadoHembra);

    GanadoHembra encontrarGanadoHembra(GanadoHembra ganadoHembra);

    void darBajaGanadoHembra(GanadoHembra ganadoHembra);

    GanadoHembra encontrarGanadoHembraPorId(Long id);

}
