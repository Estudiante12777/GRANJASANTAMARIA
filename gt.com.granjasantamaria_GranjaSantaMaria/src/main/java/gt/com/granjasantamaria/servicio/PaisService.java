package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Pais;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface PaisService {

    // METODOS A IMPLEMENTAR 
    public List<Pais> listadoPais();

    public void guardarPais(Pais pais);

    public void eliminarPais(Pais pais);

    public Pais encontrarPais(Pais pais);

    public void darBajaPais(Pais pais);

}
