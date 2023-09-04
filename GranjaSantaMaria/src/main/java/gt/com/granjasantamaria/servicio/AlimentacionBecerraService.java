package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import gt.com.granjasantamaria.modelo.Becerra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yaml.snakeyaml.events.AliasEvent;

import java.util.List;

public interface AlimentacionBecerraService {

    Page<Object[]> obtenerAlimentacionBecerraPaginado(Pageable pageable);

    void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    void darDeBajaAlimentacionBecerra(Long idAlimentacionBecerra);

    AlimentacionBecerra encontrarAlimentacionBecerraPorId(Long idAlimentacionBecerra);

    String encontrarNombreBecerraPorIdAlimentacionBecerra(Long idAlimentacionBecerra);

}
