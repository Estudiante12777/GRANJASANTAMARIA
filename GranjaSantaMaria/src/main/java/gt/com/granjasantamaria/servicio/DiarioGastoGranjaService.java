package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface DiarioGastoGranjaService {

    public List<DiarioGastoGranja> obtenerListadoDiarioGastosGranja();

    public void guardarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja);

    public void eliminarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja);

    public DiarioGastoGranja encontrarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja);

    public void darBajaDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja);

}
