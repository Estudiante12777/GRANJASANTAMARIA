package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DetalleHistorialClinicoMachoService {

    public Page<DetalleHistorialClinicoMacho> obtenerListadoDetalleHistorialClinicoMachos(Long idHistorialClinicoMacho, Pageable pageable);

    public void guardarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

    public void eliminarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

    public DetalleHistorialClinicoMacho encontrarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

    public void darBajaDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho);

}
