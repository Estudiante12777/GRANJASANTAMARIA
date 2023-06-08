package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ProduccionDiariaLecheService {

    List<ProduccionDiariaLeche> obtenerListaProduccionDiariaLeche();

    List<ProduccionDiariaLeche> obtenerListaTotalProduccionLeche();

    List<ProduccionDiariaLeche> obtenerListaTotalProduccionDiariaLeche();

    Page<ProduccionDiariaLeche> obtenerProduccionDiaraLechePaginado(Pageable pageable);

    void guardarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    void eliminarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    ProduccionDiariaLeche encontrarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    List<ProduccionDiariaLeche> encontrarTotalProduccionFecha(LocalDate fechaInicio, LocalDate fechaFin);

    void darDeBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

}
