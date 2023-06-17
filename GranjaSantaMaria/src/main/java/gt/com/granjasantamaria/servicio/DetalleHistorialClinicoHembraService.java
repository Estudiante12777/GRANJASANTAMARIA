package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DetalleHistorialClinicoHembraService {

    public Page<DetalleHistorialClinicoHembra> obtenerListadoDetalleHistorialClinicoHembraPaginado(Pageable pageable);

    public void guardarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    public void eliminarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    public DetalleHistorialClinicoHembra encontrarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    public void darBajaDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

}
