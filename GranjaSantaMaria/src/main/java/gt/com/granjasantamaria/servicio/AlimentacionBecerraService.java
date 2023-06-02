package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface AlimentacionBecerraService {

    public List<AlimentacionBecerra> obtenerListadoAlimentacionBecerras();

    public void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    public void eliminarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    public AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    public void darDeBajaAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

}
