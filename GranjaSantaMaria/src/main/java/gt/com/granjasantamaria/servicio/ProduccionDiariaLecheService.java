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

    Page<ProduccionDiariaLeche> obtenerProduccionDiaraLechePaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    Page<ProduccionDiariaLeche> obtenerProduccionDiaraLechePaginadoPorFechaAndIdGanadoHembra(LocalDate fechaInicio, LocalDate fechaFin, Long idGanadoHembra, Pageable pageable);

    void guardarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    ProduccionDiariaLeche encontrarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    List<ProduccionDiariaLeche> encontrarTotalProduccionFecha(LocalDate fechaInicio, LocalDate fechaFin);

    List<ProduccionDiariaLeche> encontrarTotalProduccionFechaAndIdGanadoHembra(LocalDate fechaInicio, LocalDate fechaFin, Long idGanadoHembra);

    void darDeBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

}
