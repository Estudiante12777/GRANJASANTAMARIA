package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Municipio;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface MunicipioService {

    public List<Municipio> listadoMunicipios();

    public void guardarMunicipio(Municipio municipio);

    public void eliminarMunicipio(Municipio municipio);

    public Municipio encontrarMunicipio(Municipio municipio);

    public void darBajaMunicipio(Municipio municipio);

}
