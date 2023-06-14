package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.TipoGanado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipoGanadoService {

    public List<TipoGanado> listadoTiposGanado();

    public Page<TipoGanado> obtenerListadoTipoGanadoPaginado(Pageable pageable);

    public void guardarTipoGanado(TipoGanado tipoGanado);

    public void eliminarTipoGanado(TipoGanado tipoGanado);

    public TipoGanado encontrarTipoGanado(TipoGanado tipoGanado);

    public void darBajaTipoGanado(TipoGanado tipoGanado);

}
