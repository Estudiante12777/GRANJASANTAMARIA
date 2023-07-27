package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlimentacionBecerroService {

    Page<AlimentacionBecerro> obtenerListadoAlimentacionBecerroPaginado(Pageable pageable);

    void guardarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

    AlimentacionBecerro encontrarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

    void darDeBajaAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro);

}
