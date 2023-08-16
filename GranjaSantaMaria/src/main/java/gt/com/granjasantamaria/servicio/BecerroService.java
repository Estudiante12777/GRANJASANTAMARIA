package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Becerro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BecerroService {

    List<Becerro> listadoBecerros();

    Page<Becerro> listadoBecerrosPaginado(Pageable pageable);

    void guardarBecerro(Becerro becerro);

    Becerro encontrarBecerro(Becerro becerro);

    void darBajaBecerro(Becerro becerro);

}
