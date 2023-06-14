package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlimentacionBecerraService {

    public Page<AlimentacionBecerra> obtenerAlimentacionBecerraPaginado(Pageable pageable);

    public void guardarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    public void eliminarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    public AlimentacionBecerra encontrarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

    public void darDeBajaAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra);

}
