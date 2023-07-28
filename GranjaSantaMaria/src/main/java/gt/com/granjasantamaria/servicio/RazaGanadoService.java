package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RazaGanadoService {

    List<RazaGanado> listadoRazasGanado();

    Page<RazaGanado> obtenerListadoRazaGanadoPaginado(Pageable pageable);

    void guardarRazaGanado(RazaGanado razaGanado);

    RazaGanado encontrarRazaGando(RazaGanado razaGanado);

    void darBajaRazaGanado(RazaGanado razaGanado);

}
