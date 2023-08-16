package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Becerra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BecerraService {

    List<Becerra> listadoBecerras();

    Page<Becerra> listadoBecerrasPaginado(Pageable pageable);

    void guardarBecerra(Becerra becerra);

    Becerra encontrarBecerra(Becerra becerra);

    void darBajaBecerra(Becerra becerra);

}
