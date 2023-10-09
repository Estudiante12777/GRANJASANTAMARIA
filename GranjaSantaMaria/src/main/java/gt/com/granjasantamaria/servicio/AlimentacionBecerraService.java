package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlimentacionBecerraService {

    Page<Object[]> obtenerAlimentacionBecerraPaginado(Pageable pageable);

    void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    void darDeBajaAlimentacionBecerra(Long idAlimentacionBecerra);

    AlimentacionBecerra encontrarAlimentacionBecerraPorId(Long idAlimentacionBecerra);

    String encontrarNombreBecerraPorIdAlimentacionBecerra(Long idAlimentacionBecerra);

}
