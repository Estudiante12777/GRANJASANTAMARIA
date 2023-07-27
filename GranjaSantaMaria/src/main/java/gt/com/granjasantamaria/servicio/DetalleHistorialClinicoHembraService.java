package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoHembra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetalleHistorialClinicoHembraService {

    Page<DetalleHistorialClinicoHembra> obtenerListadoDetalleHistorialClinicoHembraPaginado(Long idHistorialClinicoHembra, Pageable pageable);

    void guardarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    void eliminarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    DetalleHistorialClinicoHembra encontrarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

    void darBajaDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra);

}
