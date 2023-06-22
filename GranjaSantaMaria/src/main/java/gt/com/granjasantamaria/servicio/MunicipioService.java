package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MunicipioService {

    public List<Municipio> listadoMunicipios();

    public Page<Municipio> listadoMunicipioPaginado(Pageable pageable);

    public void guardarMunicipio(Municipio municipio);

    public void eliminarMunicipio(Municipio municipio);

    public Municipio encontrarMunicipio(Municipio municipio);

    public List<Municipio> obtenerMunicipiosPorDepartamento(Long idDepartamento);

    public void darBajaMunicipio(Municipio municipio);

}
