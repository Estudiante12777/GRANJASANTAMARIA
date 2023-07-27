package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.TipoGanado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipoGanadoService {

    List<TipoGanado> listadoTiposGanado();

    Page<TipoGanado> obtenerListadoTipoGanadoPaginado(Pageable pageable);

    void guardarTipoGanado(TipoGanado tipoGanado);

    void eliminarTipoGanado(TipoGanado tipoGanado);

    TipoGanado encontrarTipoGanado(TipoGanado tipoGanado);

    void darBajaTipoGanado(TipoGanado tipoGanado);

}
