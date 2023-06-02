package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface ProduccionDiariaLecheService {

    List<ProduccionDiariaLeche> obtenerListaProduccionDiariaLeche();

    List<ProduccionDiariaLeche> obtenerListaTotalProduccionLeche();

    List<ProduccionDiariaLeche> obtenerListaTotalProduccionDiariaLeche();

    void guardarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    void eliminarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    ProduccionDiariaLeche encontrarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    List<ProduccionDiariaLeche> encontrarTotalProduccionFecha(LocalDate fechaInicio, LocalDate fechaFin);

    void darDeBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

}
