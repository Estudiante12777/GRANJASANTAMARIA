package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface DiarioGastoGranjaService {

    List<DiarioGastoGranja> obtenerListadoDiarioGastosGranja();

    List<DiarioGastoGranja> obtenerListaTotalDiarioGastoGranja();

    Page<DiarioGastoGranja> obtenerListaTotalDiarioGastoGranjaPaginadoPorFecha(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    void guardarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja);

    void eliminarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja);

    DiarioGastoGranja encontrarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja);

    List<DiarioGastoGranja> encontrarTotalDiarioGastoGranja(LocalDate fechaInicio, LocalDate fechaFin);

    void darBajaDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja);

    Double obtenerTotalGasto();

}
