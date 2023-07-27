package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlimentacionBecerraService {

    Page<AlimentacionBecerra> obtenerAlimentacionBecerraPaginado(Pageable pageable);

    void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    void eliminarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    void darDeBajaAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

}
