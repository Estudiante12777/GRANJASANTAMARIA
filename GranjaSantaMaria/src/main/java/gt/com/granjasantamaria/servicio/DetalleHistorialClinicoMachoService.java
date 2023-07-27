package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetalleHistorialClinicoMachoService {

    Page<DetalleHistorialClinicoMacho> obtenerListadoDetalleHistorialClinicoMachos(Long idHistorialClinicoMacho, Pageable pageable);

    void guardarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

    DetalleHistorialClinicoMacho encontrarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

    void darBajaDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

}
