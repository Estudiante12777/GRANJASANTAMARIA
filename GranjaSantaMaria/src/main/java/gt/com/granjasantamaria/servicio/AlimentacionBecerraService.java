package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import gt.com.granjasantamaria.modelo.AlimentacionBecerro;
import gt.com.granjasantamaria.modelo.Becerra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlimentacionBecerraService {
    Page<AlimentacionBecerra> listadoAlimentacionBecerras(Pageable pageable);

    void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    void darBajaAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    List<Becerra> obtenerRelacionMadreBecerra(AlimentacionBecerra alimentacionBecerra);

    AlimentacionBecerra obtenerProduccionDiariaLechePorIdAlimentacionBecerra(Long idAlimentacionBecerra);
}
