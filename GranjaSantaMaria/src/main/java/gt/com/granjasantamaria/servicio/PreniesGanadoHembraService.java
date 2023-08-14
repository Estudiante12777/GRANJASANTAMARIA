package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import gt.com.granjasantamaria.modelo.PreniesGanadoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PreniesGanadoHembraService {

    Page<PreniesGanadoHembra> obtenerListadoPreniesGanadoHembraPaginado(Pageable pageable);

    void guardarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra);

    PreniesGanadoHembra encontrarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra);

    void darBajaPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra);

    List<PreniesGanadoHembra> obtenerListadoPreniesGanadoHembraPorGanadoHembra(GanadoHembra ganadoHembra);

}
