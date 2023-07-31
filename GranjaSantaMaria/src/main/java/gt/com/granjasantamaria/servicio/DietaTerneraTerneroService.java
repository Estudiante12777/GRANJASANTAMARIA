package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DietaTerneraTernero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DietaTerneraTerneroService {

    Page<DietaTerneraTernero> obtenerListadoDietaTerneraTerneroPaginado(Pageable pageable);

    void guardarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero);

    DietaTerneraTernero encontrarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero);

    void darBajaDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero);

}
