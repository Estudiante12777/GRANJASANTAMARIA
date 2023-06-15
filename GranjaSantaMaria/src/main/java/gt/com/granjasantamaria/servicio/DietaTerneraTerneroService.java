package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DietaTerneraTernero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DietaTerneraTerneroService {

    public Page<DietaTerneraTernero> obtenerListadoDietaTerneraTerneroPaginado(Pageable pageable);

    public void guardarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero);

    public void eliminarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero);

    public DietaTerneraTernero encontrarDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero);

    public void darBajaDietaTerneraTernero(DietaTerneraTernero dietaTerneraTernero);

}
