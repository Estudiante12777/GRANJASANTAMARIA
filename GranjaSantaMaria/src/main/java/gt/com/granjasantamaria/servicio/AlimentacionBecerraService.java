package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yaml.snakeyaml.events.AliasEvent;

public interface AlimentacionBecerraService {

    Page<Object[]> obtenerAlimentacionBecerraPaginado(Pageable pageable);

    void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    void darDeBajaAlimentacionBecerra(Long idAlimentacionBecerra);

    AlimentacionBecerra encontrarAlimentacionBecerraPorId(Long idAlimentacionBecerra);

}
