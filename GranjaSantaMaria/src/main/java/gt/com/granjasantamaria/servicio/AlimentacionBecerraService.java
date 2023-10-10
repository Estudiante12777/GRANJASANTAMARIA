package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlimentacionBecerraService {

    void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

}
