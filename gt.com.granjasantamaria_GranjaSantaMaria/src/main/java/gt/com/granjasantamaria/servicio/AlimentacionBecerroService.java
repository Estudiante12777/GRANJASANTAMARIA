package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerro;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface AlimentacionBecerroService {

    public List<AlimentacionBecerro> obtenerListadoAlimentacionBecerros();

    public void guardarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

    public void eliminarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

    public AlimentacionBecerro encontrarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

    public void darDeBajaAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

}
