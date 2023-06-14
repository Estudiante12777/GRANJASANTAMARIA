package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlimentacionBecerroService {

    public Page<AlimentacionBecerro> obtenerListadoAlimentacionBecerroPaginado(Pageable pageable);

    public void guardarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

    public AlimentacionBecerro encontrarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

    public void darDeBajaAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

}
