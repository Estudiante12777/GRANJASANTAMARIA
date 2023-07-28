package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MunicipioService {

    List<Municipio> listadoMunicipios();

    Page<Municipio> listadoMunicipioPaginado(Pageable pageable);

    void guardarMunicipio(Municipio municipio);

    Municipio encontrarMunicipio(Municipio municipio);

    List<Municipio> obtenerMunicipiosPorDepartamento(Long idDepartamento);

    void darBajaMunicipio(Municipio municipio);

}
