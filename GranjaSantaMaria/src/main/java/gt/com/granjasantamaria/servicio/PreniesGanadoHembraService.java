package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import gt.com.granjasantamaria.modelo.PreniesGanadoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PreniesGanadoHembraService {

    public Page<PreniesGanadoHembra> obtenerListadoPreniesGanadoHembraPaginado(Pageable pageable);

    public void guardarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra);

    public void eliminarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra);

    public PreniesGanadoHembra encontrarPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra);

    public void darBajaPreniesGanadoHembra(PreniesGanadoHembra preniesGanadoHembra);

}
